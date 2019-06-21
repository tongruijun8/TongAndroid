package com.trj.tbase.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.trj.tbase.activity.tsocket.SocketService;
import com.trj.tlib.activity.tsocket.IBackService;

public abstract class SocketBaseActivity extends BaseActivity {

    //子类中完成抽象函数赋值
    //实体中通过实现该全局接收器方法来处理接收到消息
    public MessageBackReciver mReciver;
    private IntentFilter mIntentFilter;
    private Intent mServiceIntent;
    private LocalBroadcastManager localBroadcastManager;
    //通过调用该接口中的方法来实现数据发送
    public IBackService iBackService;
    //标记是否已经进行了服务绑定与全局消息注册
    private boolean flag;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iBackService = IBackService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iBackService = null;
        }
    };

    @Override
    public void onStart() {
        flag = false;
        if (mReciver != null) {
            flag = true;
            initSocket();
            localBroadcastManager.registerReceiver(mReciver, mIntentFilter);
            bindService(mServiceIntent, conn, BIND_ABOVE_CLIENT);
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (flag == true) {
            unbindService(conn);
            localBroadcastManager.unregisterReceiver(mReciver);
        }
        super.onDestroy();
    }

    public void initSocket() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //mReciver = new MessageBackReciver();
        mServiceIntent = new Intent(this, SocketService.class);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(SocketService.HEART_BEAT_ACTION);
        mIntentFilter.addAction(SocketService.MESSAGE_ACTION);
    }

    public abstract static class MessageBackReciver extends BroadcastReceiver {
        @Override
        public abstract void onReceive(Context context, Intent intent);
    }


}
