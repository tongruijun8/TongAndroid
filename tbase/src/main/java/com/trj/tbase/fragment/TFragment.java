package com.trj.tbase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trj.tbase.activity.InitActivity;
import com.trj.tbase.mvp.TView;


/**
 * Created by Administrator on 2017/10/20.
 */

public class TFragment extends Fragment implements TView {

    public InitActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (InitActivity) getActivity();
    }

    /**
     * 用于初始化请求数据
     */
    public void initData(){

    }

    //-----------------------请求返回的方法--------------
    @Override
    public void tPostError(String errorMsg) {
        activity.tPostError(errorMsg);
    }

    @Override
    public void tRemind(String message) {
        activity.tRemind(message);
    }

    @Override
    public void tPostFinish(int code) {
        activity.tPostFinish(code);
    }

    @Override
    public <B> void tPostFail(B b) {
        activity.tPostFail(b);
    }
}
