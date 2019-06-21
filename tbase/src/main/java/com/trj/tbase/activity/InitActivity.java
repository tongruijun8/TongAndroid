package com.trj.tbase.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.trj.tbase.R;
import com.trj.tbase.mvp.TView;
import com.trj.tbase.manage.ActivityManager;
import com.trj.tbase.manage.NetWorkManage;
import com.trj.tbase.tdialog.TLoadingDialog;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

public class InitActivity extends AppCompatActivity implements TView {

    private boolean stateBar = true;

    public Context context;
    public View rootView;
    public ActivityManager activityManager;
    public NetWorkManage netWorkManage;


    /**
     * Activity的初始化方法
     */
    protected void initWork() {
        Logger.t("-------initWork--------1");
        context = this;
        netWorkManage = NetWorkManage.getInstance();
        activityManager = ActivityManager.getInstance();
        activityManager.addActivity(this);
        rootView = findViewById(android.R.id.content);
    }

    protected void initView(){

    }

    /**
     * 设置状态栏字体的颜色：true为黑色，反之为白色
     *
     * @param stateBar
     */
    public void setStateBar(boolean stateBar) {
        this.stateBar = stateBar;
    }

    public void changeStateBar(boolean stateBar) {
        if (activityManager != null) {
            activityManager.setStateBarColor(stateBar, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (activityManager != null) {
            activityManager.setStateBarColor(stateBar, this);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backBefore()) {
                finish();
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityManager != null) {
            activityManager.removeActivity(this);
        }
    }

    /**
     * 返回之前(默认返回上一层)
     */
    protected boolean backBefore() {
        return true;
    }

    //------------------------------------网络请求提示框--------------------------------------------

    private TLoadingDialog loadingDialog = null;

    public void showDialog(String msg) {
        loadingDialog = new TLoadingDialog.Builder(context)
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setMessage(msg)
                .create();
        loadingDialog.show(getSupportFragmentManager(), "tishi_post");
    }

    public void hideDialog() {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    //动态设置text
    public void setDialogText(String msg) {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.setMessage(msg);
            }
        }
    }

    //-----------------------请求返回的方法--------------

    @Override
    public <B> void tPostFail(B b) {

    }

    @Override
    public void tPostError(String errorMsg) {
        if(TextUtils.isEmpty(errorMsg)){
            return;
        }
        Logger.t("error = " + errorMsg);
        if (errorMsg.toLowerCase().contains("failed to connect")) {
            ToastUtil.showToast(context,"服务器找不到了");
        }
    }

    @Override
    public void tRemind(String message) {
        if(TextUtils.isEmpty(message)){
            return;
        }
        ToastUtil.showToast(context,message);
    }

    @Override
    public void tPostFinish(int code) {
        hideDialog();
    }

    //------------------------------------   其它   --------------------------------------------

    protected String getVersion() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
//        setActivityAnim(android.R.anim.fade_in, android.R.anim.fade_out);
//        setActivityAnim(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setStartActivityAnim(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public void finish() {
        super.finish();
//        setActivityAnim(android.R.anim.fade_in, android.R.anim.fade_out);
//        setActivityAnim(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Logger.t("activityName = " + getClass().getName());
        setEndActivityAnim(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }

    /**
     * 开始activity的动画
     * 更改动画，可以重写此方法
     *
     * @param enterAnim
     * @param exitAnim
     */
    public void setStartActivityAnim(int enterAnim, int exitAnim) {
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 结束activity的动画
     * 更改动画，可以重写此方法
     *
     * @param enterAnim
     * @param exitAnim
     */
    public void setEndActivityAnim(int enterAnim, int exitAnim) {
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 跳转activity
     *
     * @param cls
     */
    public void skipActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }

}
