package com.trj.demo2.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

public class HttpBase {

    public static String encodeStr = "application/json; charset=utf-8";
    public static String encodeStr2 = "application/x-www-form-urlencoded; charset=utf-8";

    public static String headerName = "";
    public static String headerInfo = "";

    /**
     * 超时时间20s
     */
    protected static long DEFAULT_TIMEOUT = 20;

    /**
     * 添加统一header,超时时间,http日志打印
     * body采用UTF-8编码
     *
     * @return
     */
    protected static OkHttpClient genericClientJson() {
        //新建log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
            //打印retrofit日志
            Logger.getGlobal().log(Level.INFO, "http===========" + message);
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("Content-Type", encodeStr2);
            if (headerName != null && !headerName.equals("")) {
                requestBuilder.addHeader(headerName, headerInfo);
            }
            requestBuilder.post(RequestBody.create(MediaType.parse(encodeStr),
                    bodyToString(request.body())));
            request = requestBuilder.build();
            return chain.proceed(request);
        });
        return builder.build();
    }

    /**
     * 添加统一header,超时时间,http日志打印
     * body采用UTF-8编码
     *
     * @return
     */
    protected static OkHttpClient genericClientCommon() {
        //新建log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
            //打印retrofit日志
            Logger.getGlobal().log(Level.INFO, "http===========" + message);
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("Content-Type", HttpBase.encodeStr2);
            if(headerName != null && !headerName.equals("")){
                requestBuilder.addHeader(headerName, headerInfo);
            }
            request = requestBuilder.build();
            return chain.proceed(request);
        });
        return builder.build();
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * 将json串转化为RequestBody对象
     *
     * @param jsonStr
     * @return
     */
    public static RequestBody getRequestBody(String jsonStr) {
        return RequestBody.create(MediaType.parse(HttpBase.encodeStr), jsonStr);
    }

    /**
     * 将json串转化为RequestBody对象
     * @param jsonStr json字符串
     * @param encodeStr 数据的编码格式
     * @return
     */
    public static RequestBody getRequestBody(String jsonStr,String encodeStr) {
        return RequestBody.create(MediaType.parse(encodeStr), jsonStr);
    }

}
