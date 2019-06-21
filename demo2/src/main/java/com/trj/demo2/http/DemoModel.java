package com.trj.demo2.http;

import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.tbase.mvp.TModel;
import com.trj.tlib.uils.MD5Utils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DemoModel extends TModel {

    private DemoHttpRetrofit httpRetrofit;

    public DemoModel() {
        httpRetrofit = DemoHttpRetrofit.getInstance();
        HttpBase.headerName = "appBaseInfo";
        HttpBase.headerInfo = "{\"apptype\":\"android\",\"appversion\":\"1.0.2\",\"deviceName\":\"Xiaomi\",\"model\":\"user\",\"requesttime\":\"\",\"token\":\"cDw8/rmyJ5gJ8U+7gAFbZqKeK3u9NHBRulvafbEhna4\\u003d\",\"userName\":\"13729501678\"}";
    }

    public void postLogin(String phone, String psw, Subscriber<RespBean<Users>> subscriber) {
        httpRetrofit.httpAPI.login(phone, MD5Utils.getMD5(psw, true))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void postUserAll(int page, int pageSize, Subscriber<RespBean<PageBean<Users>>> subscriber) {
        httpRetrofit.httpAPI.userAll2(page, pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void postUserAdd(String phone, String psw, Subscriber<RespBean> subscriber) {
        httpRetrofit.httpAPI.userAdd(phone, psw)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void postUserAddMore(String contactsJsonStr, Subscriber<RespBean> subscriber) {
        httpRetrofit.httpAPI.userAddMore(contactsJsonStr)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void test(Subscriber<RespBean> subscriber) {
        String dataStr = "{\"data\":{\"approverList\":[{\"id\":1094}],\"content\":\"155588\",\"courseList\":[],\"duration\":\"0.3\",\"end_time\":\"2019-02-25 22:22\",\"leave_type\":0,\"start_time\":\"2019-02-25 15:22\"}}";
        httpRetrofit.httpAPI.saveLeave(dataStr)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
