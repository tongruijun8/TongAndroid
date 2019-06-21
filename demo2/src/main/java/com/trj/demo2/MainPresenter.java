package com.trj.demo2;

import android.view.View;

public class MainPresenter {

    private MainView mainView;

    private MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();
    }

    protected void clickBtn(View view){
        mainModel.clickBtn(view,new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainView.onClickBtn(v);
            }
        });
    }



}
