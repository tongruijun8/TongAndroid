package com.trj.demo2.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.google.gson.Gson;
import com.trj.demo2.R;
import com.trj.demo2.base.DMVPActivity;
import com.trj.demo2.bean.Users;
import com.trj.demo2.bean.req.ReqLoginBean;
import com.trj.demo2.databinding.ActivityLoginBinding;
import com.trj.demo2.http.DemoModel;
import com.trj.tlib.uils.Logger;

public class LoginActivity extends DMVPActivity<LoginView,LoginPresenter> implements LoginView {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    @Override
    protected LoginPresenter initPersenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    private ReqLoginBean bean;

    @Override
    protected void initView() {
        bean = new ReqLoginBean();
        bean.setUserName("15091288100");
        bean.setUserPsw("123456");

        //关联数据
        binding.setBean(bean);
        binding.setEvent(new LoginEvent());
    }

    public void onClickLogin(View view) {
        Logger.t("postInfo = " + new Gson().toJson(bean));
        showDialog("登陆中");
        getPresenter().login(bean.getUserName(), bean.getUserPsw());
    }

    @Override
    public void loginSuccess(Users users) {
        Logger.t("result = " + new Gson().toJson(users));

        skipActivity(MainNavBottomActivity.class);
    }

    public class LoginEvent{

        public void phoneTextChanged(Editable s) {
            bean.setUserName(s.toString());
        }
        public void pswTextChanged(Editable s) {
            bean.setUserPsw(s.toString());
        }

    }

}
