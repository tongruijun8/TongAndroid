package com.trj.demo2.bean.req;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ReqLoginBean extends BaseObservable {

    private String userName;

    private String userPsw;
    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Bindable
    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }
}
