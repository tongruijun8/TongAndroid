package com.trj.tbase.activity.tsocket;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.trj.tlib.activity.tsocket.IBackService;
import com.trj.tlib.uils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketService extends Service {

    private static final String TAG = "BackService";
    //心跳包频率
    private static final long HEART_BEAT_RATE = 1000;

    public static final String HOST = "192.168.1.131";// //
    public static final int PORT = 8080;

    public static final String MESSAGE_ACTION = "com.splxtech.powermanagor.engine.socket";
    public static final String HEART_BEAT_ACTION = "com.splxtech.powermanagor.engine.socket.heart";

    public static String HEART_BEAT_STRING = "00";//心跳包内容

    private ReadThread mReadThread;

    private LocalBroadcastManager mLocalBroadcastManager;

    private WeakReference<Socket> mSocket;

    // For heart Beat
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {

        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                boolean isSuccess = sendMsg(HEART_BEAT_STRING);//就发送一个HEART_BEAT_STRING过去 如果发送失败，就重新初始化一个socket
                if (!isSuccess) {
                    mHandler.removeCallbacks(heartBeatRunnable);
                    mReadThread.release();
                    releaseLastSocket(mSocket);
                    new InitSocketThread().start();
                }
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    private long sendTime = 0L;
    private IBackService.Stub iBackService = new IBackService.Stub() {

        @Override
        public boolean sendMessage(String message){
            return sendMsg(message);
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        Logger.t("onBind " );
        return iBackService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.t("onCreate " );
        new InitSocketThread().start();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

    }

    public boolean sendMsg(String msg) {
        Logger.t("sendMsg " );
        if (null == mSocket || null == mSocket.get()) {
            return false;
        }
        Socket soc = mSocket.get();
        try {
            if (!soc.isClosed() && !soc.isOutputShutdown()) {
                OutputStream os = soc.getOutputStream();
                os.write(msg.getBytes());
                os.flush();
                sendTime = System.currentTimeMillis();//每次发送成数据，就改一下最后成功发送的时间，节省心跳间隔时间
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void initSocket() {//初始化Socket
        Logger.t("initSocket " );
        try {
            Socket so = new Socket(HOST, PORT);
            mSocket = new WeakReference<>(so);
            mReadThread = new ReadThread(so);
            mReadThread.start();
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//初始化成功后，就准备发送心跳包
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Logger.t("error = " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Logger.t("error = " + e.getMessage());
        }
    }

    private void releaseLastSocket(WeakReference<Socket> mSocket) {
        Logger.t("releaseLastSocket " );
        try {
            if (null != mSocket) {
                Socket sk = mSocket.get();
                if (!sk.isClosed()) {
                    sk.close();
                }
                sk = null;
                mSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            initSocket();
        }
    }

    // Thread to read content from Socket
    class ReadThread extends Thread {
        private WeakReference<Socket> mWeakSocket;
        private boolean isStart = true;

        public ReadThread(Socket socket) {
            mWeakSocket = new WeakReference<>(socket);
        }

        public void release() {
            isStart = false;
            releaseLastSocket(mWeakSocket);
        }

        @Override
        public void run() {
            super.run();
            Socket socket = mWeakSocket.get();
            if (null != socket) {
                try {
                    InputStream is = socket.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int length = 0;
                    while (!socket.isClosed() && !socket.isInputShutdown()
                            && isStart && ((length = is.read(buffer)) != -1)) {
                        if (length > 0) {
                            String message = new String(Arrays.copyOf(buffer,
                                    length)).trim();
                            Log.e(TAG, message);
                            //收到服务器过来的消息，就通过Broadcast发送出去
                            if (message.equals(HEART_BEAT_STRING)) {//处理心跳回复
                                Intent intent = new Intent(HEART_BEAT_ACTION);
                                mLocalBroadcastManager.sendBroadcast(intent);
                            } else {
                                //其他消息回复
                                Intent intent = new Intent(MESSAGE_ACTION);
                                intent.putExtra("message", message);
                                mLocalBroadcastManager.sendBroadcast(intent);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
