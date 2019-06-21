package com.trj.tbase.activity;

import android.net.Uri;
import android.webkit.JsPromptResult;

public interface TWebJsConstraint {

    /**
     * 获取 js 调用 android 的方法名
     * @param methodName 获取到的方法名
     * @param uri Uri对象，可以获取到相关参数；（也可以获取到方法名）
     * @param result JsPromptResult 对象，可以响应结果:cancel()；通过此对象可以回传参数：confirm(String)
     */
    void getMethodName(String methodName, Uri uri, JsPromptResult result);

    /**
     * android 调用 js 方法
     * @param jsMethodName js 方法名
     */
    void androidToJsMethod(String jsMethodName);

}
