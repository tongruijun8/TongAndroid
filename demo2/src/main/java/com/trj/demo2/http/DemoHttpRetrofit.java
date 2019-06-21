package com.trj.demo2.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoHttpRetrofit extends HttpBase {

    // baseUrl 不能为空
    private static String baseUrl = "http://192.168.1.152:8080/tandroid/";

    private volatile static DemoHttpRetrofit mInstance;

    public DemoAPI httpAPI;

    private DemoHttpRetrofit() {
//        DEFAULT_TIMEOUT = 10;//设置默认的超时时间（默认20s）
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持Rxjava
                .baseUrl(baseUrl)
                .client(genericClientCommon());
        httpAPI = builder.build().create(DemoAPI.class);
    }

    /**
     * 请求体为键值对
     * @return
     */
    public static DemoHttpRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (DemoHttpRetrofit.class) {
                if (mInstance == null){
                    mInstance = new DemoHttpRetrofit();
                }
            }
        }
        return mInstance;
    }

}
