package com.trj.tbase.manage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


// 判断是否有网络连接

/**
 * 网络连接管理类
 *  ConnectivityManager类：有四个主要任务
 * 1、监听手机网络状态（包括GPRS，WIFI， UMTS等)
 * 2、手机状态发生改变时，发送广播
 * 3、当一个网络连接失败时进行故障切换
 * 4、为应用程序提供可以获取可用网络的高精度和粗糙的状态
 */
public class NetWorkManage{

    public NetWorkManage() {
    }

    private static NetWorkManage instance;

    public static NetWorkManage getInstance() {
        if (null == instance) {
            instance = new NetWorkManage();
        }
        return instance;
    }

    /**
     * 判断是否有手机网络连接
     *
     * @param context
     * @return 连接成功都返回true ,反之则返回false
     */
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI连接是否可用
     *
     * @param context 上下文
     * @return 连接成功都返回true ,反之则返回false
     */
    public boolean isWifiConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWiFiNetworkInfo != null) {
            return mWiFiNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取当前的网络连接是否可用 （1.任意一种网络连接成功都返回true 2.否则返回false）
     *
     * @param context 上下文
     * @return 任意一种网络连接成功都返回true ,反之则返回false
     */
    public boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager
                .getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

}
