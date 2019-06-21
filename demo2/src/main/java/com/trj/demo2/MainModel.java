package com.trj.demo2;

import android.view.View;

public class MainModel {

    protected void clickBtn(View view,View.OnClickListener listener){
        view.setOnClickListener(listener);
    }

}
