package com.trj.demo2.activity.fragment;

import android.view.View;
import android.widget.TextView;

import com.trj.demo2.R;
import com.trj.demo2.test.TestTDialogActivity;
import com.trj.tbase.fragment.TInitFragment;


public class MainThreeFragment extends TInitFragment implements View.OnClickListener {

    private TextView tdialogText;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void initFragmentView(View view) {
        tdialogText = view.findViewById(R.id.fm_three_tdialog);
        tdialogText.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.fm_three_tdialog) {
            activity.skipActivity(TestTDialogActivity.class);
        }
    }
}
