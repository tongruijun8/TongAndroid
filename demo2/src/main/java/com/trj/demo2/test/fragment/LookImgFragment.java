package com.trj.demo2.test.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.tbase.fragment.TInitFragment;

public class LookImgFragment extends TInitFragment {

    private int code;

    public LookImgFragment() {
    }

    @SuppressLint("ValidFragment")
    public LookImgFragment(int code) {
        this.code = code;
    }

    @Override
    protected int initLayout() {
        return R.layout.item_image;
    }

    private ImageView imageView;

    @Override
    protected void initFragmentView(View view) {
        imageView = view.findViewById(R.id.item_imageview);
    }

    @Override
    public void initData() {

    }
}
