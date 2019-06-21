package com.trj.demo2.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TestHandlerActivity extends BaseActivity {


    static final String UPPER_NUM = "upper";

    CalThread calThread;

    EditText edittext;

    private TextView jgtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("Handler测试", true);
        edittext = findViewById(R.id.edittext);
        jgtext = findViewById(R.id.jgtext);

        calThread = new CalThread();
        // 启动新线程
        calThread.start();
    }

    public void cal(View view) {

        // 创建消息
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM ,
                Integer.parseInt(edittext.getText().toString()));
        msg.setData(bundle);
        // 向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);

    }


    // 定义一个线程类
    class CalThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                // 定义处理消息的方法
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        final List<Integer> nums = new ArrayList<Integer>();
                        // 计算从2开始、到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            // 用i处于从2开始、到i的平方根的所有数
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                // 如果可以整除，表明这个数不是质数
                                if (i != 2 && i % j == 0) {
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }
                        // 使用Toast显示统计出来的所有质数
                        Toast.makeText(TestHandlerActivity.this, nums.toString()
                                , Toast.LENGTH_LONG).show();
//                        TestHandlerActivity.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                jgtext.setText(nums.toString());
//                            }
//                        });

                        //Runnable 将在ui 线程上运行
                        jgtext.post(new Runnable() {
                            @Override
                            public void run() {
                                jgtext.setText(nums.toString());
                            }
                        });

                    }
                }
            };
            Looper.loop();
        }
    }


    @Override
    protected void onDestroy() {
        if (calThread != null) {
            calThread = null;
            System.gc();
        }
        super.onDestroy();
    }
}
