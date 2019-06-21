package com.trj.tbase;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.request.target.ViewTarget;
import com.tencent.bugly.Bugly;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.SharedPreferencesUtils;

public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }

    /**
     * 设置Buggly 的 appid
     *
     * @param bugglyAppid
     */
    public void setBugglyAppid(String bugglyAppid) {
        Bugly.init(this, bugglyAppid, false);
    }

    protected void initAppParameter(){
        //sharePreferences 的参数
        SharedPreferencesUtils.FILE_NAME = "tong";
//        初始化打印的参数
        Logger.LOG_ENABLE = true;//是否开启打印
        Logger.tag = "tong";//打印的参数名

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
