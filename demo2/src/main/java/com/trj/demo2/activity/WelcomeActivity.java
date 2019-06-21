package com.trj.demo2.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.trj.demo2.R;
import com.trj.demo2.base.DInitActivity;

public class WelcomeActivity extends DInitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initView() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            skipActivity(MainNavBottomActivity.class);
            finish();
        }
    };

    @Override
    public void setEndActivityAnim(int enterAnim, int exitAnim) {
    }
}
