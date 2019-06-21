package com.trj.demo2.base;

import android.text.TextUtils;

import com.trj.demo2.bean.RespBean;
import com.trj.tbase.activity.InitActivity;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

public abstract class DInitActivity extends InitActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Logger.t("-------------init-------------");
        initWork();
        initView();
    }

    @Override
    public <B> void tPostFail(B b) {
        RespBean respBean = (RespBean) b;
        int errorCode = respBean.getErrorCode();
        if(errorCode == 40012){
            ToastUtil.showToast(context,"登录异常，请重新登录");
//            loginAgain();
        }else if(errorCode == 50003){
            //程序出错
            ToastUtil.showToast(context,"程序出错");
        }else{
            if (TextUtils.isEmpty(respBean.getMsg())) {
                return;
            }
            ToastUtil.showToast(context,respBean.getMsg());
        }
    }

}
