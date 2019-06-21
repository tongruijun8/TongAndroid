package com.trj.tbase.manage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * 作者：小童 on 2016/6/13 0013 15:06
 * 简介：自定义一个广播接受者,用于监听网络状态的变化
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {

    /** 无网络 */
    public static final int STYLE_NONE = -1;
    /** 手机网络 */
    public static final int STYLE_MOBILE = 0;
    /** WIFI网络 */
    public static final int STYLE_WIFI = 1;


    private OnConnectChangeListener l;

    public void setOnConnectChange(OnConnectChangeListener l) {
        this.l = l;
    }

    public interface OnConnectChangeListener {
        //网络改变的方法
        void onConnectChange(int connectStyle);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        isNetworkAvailable2(context);
    }

    public void isNetworkAvailable2(Context context) {

//        System.out.println("网络状态发生变化");
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected()) {
                if (null != l) {
                    l.onConnectChange(STYLE_WIFI);
                }
            } else if (dataNetworkInfo.isConnected()) {
                if (null != l) {
                    l.onConnectChange(STYLE_MOBILE);
                }
            } else {
                if (null != l) {
                    l.onConnectChange(STYLE_NONE);
                }
            }
        }else {
            //这里的就不写了，前面有写，大同小异
//            System.out.println("API level 大于21");
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();

            boolean isWifi = false;
            boolean isMobile = false;

            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                int type = networkInfo.getType();
                if(type == ConnectivityManager.TYPE_WIFI){
                    isWifi = true;
                }else if(type == ConnectivityManager.TYPE_MOBILE){
                    isMobile = true;
                }
            }

            if(isWifi){
                if (null != l) {
                    l.onConnectChange(STYLE_WIFI);
                }
            }else if(isMobile){
                if (null != l) {
                    l.onConnectChange(STYLE_MOBILE);
                }
            }else{
                if (null != l) {
                    l.onConnectChange(STYLE_NONE);
                }
            }

        }

    }



}
