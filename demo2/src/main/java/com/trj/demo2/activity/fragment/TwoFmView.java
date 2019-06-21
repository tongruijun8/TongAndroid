package com.trj.demo2.activity.fragment;

import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.Users;
import com.trj.tbase.mvp.TView;

public interface TwoFmView extends TView {

    void bindListData(PageBean<Users> usersList);

}
