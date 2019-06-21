package com.trj.tbase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2017/10/20.
 */

public abstract class TInitFragment extends TFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        //添加内容View
        initFragmentView(rootView);
        return rootView;
    }

    //    初始化布局
    protected abstract int initLayout();

    //    获取控件
    protected abstract void initFragmentView(View view);


}
