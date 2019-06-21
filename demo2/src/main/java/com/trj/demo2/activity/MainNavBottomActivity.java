package com.trj.demo2.activity;

import com.trj.demo2.R;
import com.trj.demo2.activity.fragment.MainFourFragment;
import com.trj.demo2.activity.fragment.MainOneFragment;
import com.trj.demo2.activity.fragment.MainThreeFragment2;
import com.trj.demo2.activity.fragment.MainTwoFragment2;
import com.trj.tbase.activity.NavBottomActivity;
import com.trj.tbase.tdialog.BaseDialog;
import com.trj.tbase.tdialog.TSelectBottomDialog;

public class MainNavBottomActivity extends NavBottomActivity {

    @Override
    protected boolean initSmoothScroll() {
        return true;//带滚动动画
    }

    @Override
    protected void initFragmentData() {
        fragmentList.add(new MainOneFragment());
        fragmentList.add(new MainTwoFragment2());
        fragmentList.add(new MainThreeFragment2());
        fragmentList.add(new MainFourFragment());
    }

    @Override
    protected int initTabMenu() {
        return R.menu.main_nav_bottom;
    }

    /**
    *
    * 作者：小童
    * 创建时间：2019/3/11 11:52
    *
    * 描述：选择Tab的方法
    *
    */
    @Override
    protected boolean selectTabItem(int position) {
//        if (position == 0){
//            titleModule.setTitleText("首页");
//            titleModule.showTitleLayout(false);
//        } else if (position == 1) {
//            titleModule.setTitleText("待定");
//            titleModule.showTitleLayout(true);
//        }  else if (position == 2) {
//            titleModule.setTitleText("事例");
//            titleModule.showTitleLayout(false);
//        }  else if (position == 3) {
//            titleModule.setTitleText("我的");
//            titleModule.showTitleLayout(true);
//        }
        return super.selectTabItem(position);
    }


    @Override
    protected boolean backBefore() {
//        activityManager.exitApp(true);

        new TSelectBottomDialog.Builder(context)
                .setText("退出系统")
                .setText("重新登录")
                .setCancelable(false)
                .setOnTdSelect(new BaseDialog.OnTdSelectListener() {
                    @Override
                    public void onTdSelect(int position) {
                        if (position == 0) {
                            activityManager.exitApp(false);
                        }else if(position == 1){
                            skipActivity(LoginActivity.class);
                        }
                    }
                })
                .create().show(getSupportFragmentManager(),"exit");

        return false;
    }
}
