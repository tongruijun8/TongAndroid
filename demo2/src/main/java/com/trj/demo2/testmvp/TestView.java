package com.trj.demo2.testmvp;

import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.tbase.mvp.TView;

public interface TestView extends TView {

    void loginSuccess(Users users);
    void postSuccess(RespBean bean);

}
