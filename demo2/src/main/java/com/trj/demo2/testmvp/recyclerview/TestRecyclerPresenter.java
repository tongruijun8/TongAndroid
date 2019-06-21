package com.trj.demo2.testmvp.recyclerview;

import android.support.annotation.NonNull;

import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.http.DPresenter;

class TestRecyclerPresenter extends DPresenter<TestRecyclerView> {

    public TestRecyclerPresenter(@NonNull TestRecyclerView view) {
        super(view);
    }

    protected void getListData(final int page, int pageSize) {

        model.postUserAll(page, pageSize, new DSubscriber<PageBean<Users>>() {

            @Override
            public void onNext(RespBean<PageBean<Users>> listRespBean) {
                if(responseState(listRespBean)){
                    if (isViewAttach()) {
                        getView().bindListData(listRespBean.getData().getList());
                    }
                }
            }

            @Override
            public void onCompleted() {
                onDFinish(1);
            }
        });
    }

}
