package com.trj.demo2.testmvp.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class TestBaseViewHolder extends BaseViewHolder {

    private ViewDataBinding binding;

    public TestBaseViewHolder(View view) {
        super(view);
        binding = DataBindingUtil.bind(view);
    }

    public RecyclerView.ViewHolder setBinding(int variableId, Object object){
        binding.setVariable(variableId,object);
        binding.executePendingBindings();
        return this;
    }

}
