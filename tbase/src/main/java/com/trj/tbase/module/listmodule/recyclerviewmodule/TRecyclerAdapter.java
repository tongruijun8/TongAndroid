package com.trj.tbase.module.listmodule.recyclerviewmodule;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public abstract class TRecyclerAdapter<B, H extends TBaseRvViewHolder> extends BaseQuickAdapter<B, H> {

    public TRecyclerAdapter(int layoutResId, @Nullable List<B> data) {
        super(layoutResId, data);
    }

    @Override
    protected abstract void convert(H helper, B item);

}
