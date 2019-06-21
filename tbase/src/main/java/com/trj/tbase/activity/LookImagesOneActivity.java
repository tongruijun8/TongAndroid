package com.trj.tbase.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bm.library.PhotoView;
import com.trj.tbase.R;
import com.trj.tlib.uils.GlideUtile;


/**
 * 查看图片
 */
public class LookImagesOneActivity extends BaseActivity {

    private PhotoView photoView;

    private String pathImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_image_one);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("", true);
        titleModule.setTitleLeftText(1 + "/" + 1);
        titleModule.setTitleLeftTextColor(R.color.color_white);
        photoView = findViewById(R.id.item_image_imageview);
        Intent intent = getIntent();
        pathImg = intent.getStringExtra("path");
        GlideUtile.bindImageView(context,pathImg,photoView);

        photoView.enable();
        photoView.setMaxScale(5);


//// 启用图片缩放功能
//        photoView.enable();
//// 禁用图片缩放功能 (默认为禁用，会跟普通的ImageView一样，缩放功能需手动调用enable()启用)
////        photoView.disenable();
//// 获取图片信息
////        Info info = photoView.getInfo();
//// 从普通的ImageView中获取Info
//        Info info = PhotoView.getImageViewInfo(photoView);
//// 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
//        photoView.animaFrom(info);
//// 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
//        photoView.animaTo(info,new Runnable() {
//            @Override
//            public void run() {
//                //动画完成监听
//            }
//        });
//// 获取/设置 动画持续时间
//        photoView.setAnimaDuring(int during);
//        int d = photoView.getAnimaDuring();
//// 获取/设置 最大缩放倍数
//        photoView.setMaxScale(float maxScale);
//        float maxScale = photoView.getMaxScale();
//// 设置动画的插入器
//        photoView.setInterpolator(Interpolator interpolator);

    }

    @Override
    public void onResume() {
        super.onResume();
        activityManager.setStateBarColor(false,this);
    }

}
