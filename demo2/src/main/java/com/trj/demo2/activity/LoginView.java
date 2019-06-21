package com.trj.demo2.activity;

import com.trj.demo2.bean.Users;
import com.trj.tbase.mvp.TView;

public interface LoginView extends TView {

    void loginSuccess(Users users);

}
