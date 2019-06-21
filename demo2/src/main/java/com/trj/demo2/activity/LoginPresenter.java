package com.trj.demo2.activity;

import android.support.annotation.NonNull;

import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.http.DPresenter;
import com.trj.tlib.uils.TUtils;

import org.apache.commons.lang3.StringUtils;

public class LoginPresenter extends DPresenter<LoginView> {

    public LoginPresenter(@NonNull LoginView view) {
        super(view);
    }

    protected void login(String username, String password) {

        if (StringUtils.isEmpty(username)) {
            onDRemind("手机不能为空");
            onDFinish(-1);
        } else if (StringUtils.isEmpty(password)) {
            onDRemind("密码不能为空");
            onDFinish(-1);
        } else if (!TUtils.isMobileNO(username)) {
            onDRemind("请输入正确的手机号");
            onDFinish(-1);
        } else if (password.length() < 6 || password.length() > 16) {
            onDRemind("密码有误，请确认");
            onDFinish(-1);
        } else {
            model.postLogin(username, password, new DSubscriber<Users>() {
                @Override
                public void onNext(RespBean<Users> listRespBean) {
                    if(responseState(listRespBean)){
                        if (isViewAttach()) {
                            getView().loginSuccess(listRespBean.getData());
                        }
                    }
                }
            });

        }



    }

}
