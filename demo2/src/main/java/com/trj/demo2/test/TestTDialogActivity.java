package com.trj.demo2.test;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tbase.tdialog.BaseDialog;
import com.trj.tbase.tdialog.TCommonDialog;
import com.trj.tbase.tdialog.TLoadingDialog;
import com.trj.tbase.tdialog.TLookImgsDialog;
import com.trj.tbase.tdialog.TLookImgsDialog2;
import com.trj.tbase.tdialog.TSelectBottomDialog;
import com.trj.tlib.assist.ImgPaths;
import com.trj.tlib.uils.GlideUtile;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TestTDialogActivity extends BaseActivity {

    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tdialog);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试BaseDialog", true);

        imageView1 = findViewById(R.id.tdialog_img1);
        imageView2 = findViewById(R.id.tdialog_img2);

        GlideUtile.bindImageView(context,ImgPaths.path[0],imageView1);
        GlideUtile.bindImageView(context,ImgPaths.path[1],imageView2);

    }

    public void selectBottomDialog(View view) {
        new TSelectBottomDialog.Builder(context)
                .setCancelable(false)//默认是false，不可以取消
                .setText("拍照")
                .setText("相册")
                .setOnTdSelect(position -> ToastUtil.showToast(context, "--" + position))
                .create()
                .show(getSupportFragmentManager(), "tdialog_selectbottom");
    }

    public void loadingDialog(View view) {
        new TLoadingDialog.Builder(context)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setMessage("加载中...")
                .create()
                .show(getSupportFragmentManager(), "tdialog_loading");
    }

    public void commonDialog(View view) {
        new TCommonDialog.Builder(context)
                .setIcon(R.mipmap.tdialog_icon)
                .setTitle("提示")
                .setMessage("提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息提示信息")
                .setCancelable(false)
                .setOnAffirmClick(new BaseDialog.OnTdfListener() {
                    @Override
                    public boolean onTdClick(View view, String msg) {
                        ToastUtil.showToast(context, "确认");
                        return true;
                    }
                })
                .create()
                .show(getSupportFragmentManager(), "tdialog_common");
    }

    public void lookImgDialog(View view) {

//        int[] location = new  int[2] ;
////        view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标，含toolBar
//        view.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
//
//        int ww = getResources().getDisplayMetrics().widthPixels;
//        int hh = getResources().getDisplayMetrics().heightPixels;
//
//        int viewcenterx = view.getWidth()/2+location[0];
//        int viewcentery = view.getHeight()/2+location[1];
//
//        float biliww = ww * 1.0f / location[0];
//        float bilihh = hh * 1.0f / location[1];
//        float bili = Math.min(biliww, bilihh);
//
//        AnimationSet animationSet = new AnimationSet(true);
//        ScaleAnimation scaleAnimation = new ScaleAnimation(
//                1.0f, bili, 1.0f, bili,
//                Animation.RELATIVE_TO_SELF, 1,
//                Animation.RELATIVE_TO_SELF, 1);
//        scaleAnimation.setDuration(2000);//动画持续时间
//        animationSet.addAnimation(scaleAnimation);//保存动画效果到。。
//        animationSet.setFillAfter(false);//结束后保存状态
//        animationSet.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        view.startAnimation(animationSet);//设置给控件


        int[] location = new int[2];
        view.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
        new TLookImgsDialog.Builder(context)
                .setImgPath(R.mipmap.welcome)
                .setImgPositionAndSize(location[1],location[0],view.getWidth(),view.getHeight())
//                .setOnTdLookImgClick(new BaseDialog.OnTdLookImgClickListener() {
//                    @Override
//                    public void onTdImgClick() {
//                    }
//                })
                .create()
                .show(getSupportFragmentManager(), "tdialog_lookimg");

//        testDailgo(0);

    }

    public void imageViewActivity(View view) {
        testDailgo(1);
    }


    private void testDailgo(int index){
        List<TLookImgsDialog2.ImageInfoBean> views = new ArrayList<>();
        TLookImgsDialog2.ImageInfoBean infoBean = new TLookImgsDialog2.ImageInfoBean();
        infoBean.setView(imageView1);
        infoBean.setImgPath(ImgPaths.path[0]);
        views.add(infoBean);
        TLookImgsDialog2.ImageInfoBean infoBean2 = new TLookImgsDialog2.ImageInfoBean();
        infoBean2.setView(imageView2);
        infoBean2.setImgPath(ImgPaths.path[1]);
        views.add(infoBean2);
        new TLookImgsDialog2.Builder(this)
                .setImgPath(views)
                .setPosition(index)
                .create()
                .show(getSupportFragmentManager(),"test");
    }

    public void selectImgs(View view) {
        skipActivity(LookBigImgActivity.class);
    }

    public void selectImgs2(View view) {
        skipActivity(LookMoreImgActivity.class);
    }
}
