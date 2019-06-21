package com.trj.demo2.activity.fragment;

import android.view.View;
import android.widget.TextView;

import com.trj.demo2.R;
import com.trj.tbase.fragment.TInitFragment;


public class MainOneFragment extends TInitFragment {

    private TextView fmOneText;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_one;
    }

    @Override
    protected void initFragmentView(View view) {
        fmOneText = view.findViewById(R.id.fm_one_text);
        fmOneText.setText("one");
    }

    @Override
    public void initData() {

    }

}
