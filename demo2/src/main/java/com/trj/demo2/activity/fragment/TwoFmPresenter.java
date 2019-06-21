package com.trj.demo2.activity.fragment;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.http.DPresenter;
import com.trj.tlib.bean.ContactsInfo;

import java.util.List;

public class TwoFmPresenter extends DPresenter<TwoFmView> {
    public TwoFmPresenter(@NonNull TwoFmView view) {
        super(view);
    }


    protected void getListData(final int page, int pageSize) {

        model.postUserAll(page, pageSize, new DSubscriber<PageBean<Users>>() {

            @Override
            public void onNext(RespBean<PageBean<Users>> listRespBean) {
                if(responseState(listRespBean)){
                    if (isViewAttach()) {
                        getView().bindListData(listRespBean.getData());
                    }
                }
            }

            @Override
            public void onCompleted() {
                onDFinish(1);
            }
        });
    }



    protected void addUserAll(List<ContactsInfo> contactsInfoList){

        model.postUserAddMore(new Gson().toJson(contactsInfoList), new DSubscriber() {
            @Override
            public void onNext(RespBean respBean) {

            }
        });

    }


}
