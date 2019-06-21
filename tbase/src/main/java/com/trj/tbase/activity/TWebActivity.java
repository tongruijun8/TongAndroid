package com.trj.tbase.activity;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.trj.tbase.R;

public abstract class TWebActivity extends BaseActivity implements TWebJsConstraint {


    protected WebView webView;
    private ProgressBar progressBar;

    protected WebSettings webSettings;

    private String pathUrl = "";
    private String mBeforeText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
    }

    @Override
    protected void initView() {
        super.initView();
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);

        webSettings = webView.getSettings();
        initWebSetting(webSettings);

    }

    /**
     * 可以进一步设置参数(默认已经设置的参数可以满足基本需求)
     *
     * @param webSettings
     */
    protected void initWebSetting(WebSettings webSettings){
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        // 支持本地存储
        webSettings.setDatabasePath(this.getApplicationContext().getCacheDir().getAbsolutePath());

        // 设置允许JS弹窗
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

    }

    private void init() {

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

//       统一格式
//        "appjs://demo?arg1=111&arg2=222"
//        demo为方法名；
//        appjs为固定名称；
//        arg为参数名称；（可根据需求传参）
//        统一用prompt方式访问。
        webView.setWebChromeClient(new WebChromeClient() {

            // 拦截输入框(原理同方式2)
            // 参数message:代表promt（）的内容（不是url）
            // 参数result:代表输入框的返回值
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                if (mBeforeText != null && !mBeforeText.equals("")) {
                    Uri uri = Uri.parse(message);
                    if (uri.getScheme().equals(mBeforeText)) {
                        getMethodName(uri.getAuthority(), uri, result);
                        return true;
                    }
                }
                return false;
            }

//             拦截JS的警告框
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                ToastUtil.showToast(context,"onJsAlert");
//                return super.onJsAlert(view, url, message, result);
//            }
//            // 拦截JS的确认框
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//                ToastUtil.showToast(context,"onJsConfirm");
//                return super.onJsConfirm(view, url, message, result);
//            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    // 加载中
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }

        });
    }

    /**
     * 初始化Html路径
     * @param htmlPath html 路径
     * @param beforeText 弹框交互捕获框 调用方法的前缀
     */
    protected void initHtmlPath(String htmlPath, String beforeText) {
        pathUrl = htmlPath;
        mBeforeText = beforeText;
        init();
        webView.loadUrl(pathUrl);
    }

    /**
     * 初始化 html 内容
     * @param htmlContent
     */
    protected void initHtmlContent(String htmlContent) {
        initHtmlContent(htmlContent,"");
    }

    protected void initHtmlContent(String htmlContent, String beforeText) {
        mBeforeText = beforeText;
        init();
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);
    }



    /**
     * @param methodName 方法名
     * @param uri   Uri 对象
     * @param result    JsPromptResult对象
     */
    public abstract void getMethodName(String methodName, Uri uri, JsPromptResult result);

    @Override
    public void androidToJsMethod(String jsMethodName) {
        webView.loadUrl("javascript:" + jsMethodName);
    }

    //    public boolean onTJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//
////        if ( uri.getScheme().equals("appjs")) {
////            if (uri.getAuthority().equals("getHelperToken")) {
//////                        result.confirm(getHelperToken());
////            }else if(uri.getAuthority().equals("getHelperinfo")){
//////                        result.confirm(getHelperinfo());
////            }else if(uri.getAuthority().equals("share")){
////                //                       share();
////                //                      showShare("家护帮","分享竞赛",null,"http://checkout-pk.jiahubang.com:8001/pkWeb/index.html?pkActivityId=31");
////                result.cancel();
////            }else if(uri.getAuthority().equals("closePage")){
//////                        closePage();
////                result.cancel();
////            }else{
////                result.cancel();
////            }
////            return true;
////        }
//        return false;
//    }

//    private JsConstraint jsConstraint;
//
//    public interface JsConstraint {
//        void getMethodName(String methodName,Uri uri,JsPromptResult result);
//    }


//    private EditText editText;
//    private void initPostPath() {
//        editText = new EditText(this);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.view_h));
//        editText.setLayoutParams(layoutParams);
//        editText.setText("111");
//        editText.setVisibility(View.VISIBLE);
//        AlertDialog dialog = new AlertDialog.Builder(context)
//                .setIcon(getResources().getDrawable(R.mipmap.logo))
//                .setTitle("选择请求路径：")
//                .setCancelable(false)
//                .setView(editText)
//                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String ipStr = editText.getText().toString().trim();
//                        webView.loadUrl("http://192.168.2." + ipStr + ":3000/pkWeb/index.html?pkActivityId=31");
////                        http://192.168.2.121:3000/pkWeb/index.html?pkActivityId=31
//
//                    }
//                })
//                .create();
//        dialog.show();
//    }

    //
//    /**
//     * 获取雇工编号
//     * @return
//     */
//    private String getHelperToken(){
//        return Constants.token;
//    }
//
//    /**
//     * 获取雇工编号
//     * @return
//     */
//    private String getHelperinfo(){
//        return Constants.helperCode;
//    }
//
//    /**
//     * 吊起分享
//     */
//    private void share(){
//        showShare();
//    }
//
//    /**
//     * 关闭页面
//     */
//    private void closePage(){
//        finish();
//    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(this);  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now

        // 支持本地存储
//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.clearCache(true);
        webView.clearFormData();
        getCacheDir().delete();

        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
    }

}
