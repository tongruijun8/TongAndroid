package com.trj.demo2.testmvp.recyclerview;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public abstract class TestRecyclerAdapter<B> extends BaseQuickAdapter<B, TestBaseViewHolder> {

    public TestRecyclerAdapter(int layoutResId, @Nullable List<B> data) {
        super(layoutResId, data);
    }

    @Override
    protected abstract void convert(TestBaseViewHolder helper, B item);





}
