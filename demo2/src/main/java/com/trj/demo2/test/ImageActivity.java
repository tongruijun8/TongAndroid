package com.trj.demo2.test;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tlib.uils.GlideUtile;

public class ImageActivity extends BaseActivity {

    private static final int DURATION = 300;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private ColorDrawable colorDrawable;

    ImageView imageView;
    RelativeLayout image_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    private int top, left, width, height;
    private String path;

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageview);
        image_rl = findViewById(R.id.image_rl);

        Intent intent = getIntent();
        top = intent.getIntExtra("top", 0);
        left = intent.getIntExtra("left", 0);
        width = intent.getIntExtra("width", 0);
        height = intent.getIntExtra("height", 0);
        path = intent.getStringExtra("path");

        GlideUtile.bindImageView(context,R.mipmap.welcome, imageView);// 坐标的获取设置

        int screenww = getResources().getDisplayMetrics().widthPixels;
        int screenhh = getResources().getDisplayMetrics().heightPixels;

        mLeftDelta = left;
        mTopDelta = top ;
        mWidthScale = (float) width / screenww;
        mHeightScale = (float) height / screenhh;
        colorDrawable = new ColorDrawable(Color.BLACK);
        image_rl.setBackground(colorDrawable);
//        imageView.setBackground(colorDrawable);

        enterAnimation();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBefore();
            }
        });

    }

    @Override
    protected boolean backBefore() {
        exitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
        return false;
    }

    public void setStartActivityAnim(int enterAnim, int exitAnim) {
        overridePendingTransition(0, 0);
    }

    public void setEndActivityAnim(int enterAnim, int exitAnim) {
        overridePendingTransition(0, 0);
    }

    // 进入动画
    public void enterAnimation() {
        // 设置imageview动画的初始值
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(mWidthScale);
        imageView.setScaleY(mHeightScale);
        imageView.setTranslationX(mLeftDelta);
        imageView.setTranslationY(mTopDelta);
// 设置动画
        TimeInterpolator sDecelerator = new DecelerateInterpolator();
// 设置imageview缩放动画,以及缩放开始位置
        imageView.animate().setDuration(DURATION).scaleX(1).scaleY(1).translationX(0).translationY(0).setInterpolator(sDecelerator);
// 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
        bgAnim.setDuration(DURATION);
        bgAnim.start();
    }

    public void exitAnimation(final Runnable endAction) {
        TimeInterpolator sInterpolator = new AccelerateInterpolator();
//         设置imageview缩放动画,以及缩放结束位置
        imageView.animate().setDuration(DURATION).scaleX(mWidthScale).scaleY(mHeightScale).
                translationX(mLeftDelta).translationY(mTopDelta).setInterpolator(sInterpolator).withEndAction(endAction);
//         设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
        bgAnim.setDuration(DURATION);
        bgAnim.start();
    }

    //
//    //进入动画
//    public void enterAnimation() {
//        //设置imageview动画的初始值
//        imageView.setPivotX(0);
//        imageView.setPivotY(0);
//        imageView.setScaleX(mWidthScale);
//        imageView.setScaleY(mHeightScale);
//        imageView.setTranslationX(mLeftDelta);
//        imageView.setTranslationY(mTopDelta);
//        //设置动画
//        TimeInterpolator sDecelerator = new DecelerateInterpolator();
//        //设置imageview缩放动画,以及缩放开始位置
//        imageView.animate()
//                .setDuration(DURATION)
//                .scaleX(1)
//                .scaleY(1)
//                .translationX(0)
//                .translationY(0)
//                .setInterpolator(sDecelerator);
//        // 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
//        bgAnim.setDuration(DURATION);
//        bgAnim.start();
//    }
//    public void exitAnimation(final Runnable endAction) {
//        TimeInterpolator sInterpolator = new AccelerateInterpolator();
//        //设置imageview缩放动画,以及缩放结束位置
//        imageView.animate().setDuration(DURATION)
//                .scaleX(mWidthScale)
//                .scaleY(mHeightScale)
//                .translationX(mLeftDelta)
//                .translationY(mTopDelta)
//                .setInterpolator(sInterpolator)
//                .withEndAction(endAction);
//        // 设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
//        bgAnim.setDuration(DURATION);
//        bgAnim.start();
//    }
//    进入动画: 设置动画时间、插值器和scale、translation动画效果。
    private void text1() {
//        imageView.animate()
//            .setDuration(DURATION)
//            .scaleX(1)
//                .scaleY(1)
//                .translationX(0)
//                .translationY(0)
//                .setInterpolator(sDecelerator);
//        主页类MainActivity.javapackage com.longsh.imageviewanimator;
// import android.app.Activity;
// import android.content.Intent;
// import android.os.Bundle;
// import android.view.View;
// import android.widget.ImageView;
// import com.squareup.picasso.Picasso;
// import static com.longsh.imageviewanimator.ImageActivity.HEIGHT;
// import static com.longsh.imageviewanimator.ImageActivity.IMAGE;
// import static com.longsh.imageviewanimator.ImageActivity.LEFT;
// import static com.longsh.imageviewanimator.ImageActivity.TOP;
// import static com.longsh.imageviewanimator.ImageActivity.WIDTH;
// /** * Created by q805699513 on 2017/2/2. */
// public class MainActivity extends Activity {
// private ImageView top_img; private ImageView center_img;
// private ImageView bottom_img; String image1 = "https://imgsa.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=50b3fcc5dd3f8794c7f2407cb3726591/4afbfbedab64034fba06dd7ba6c379310b551d87.jpg";
// String image2 = "https://images-cn.ssl-images-amazon.com/images/I/61PI88GEqTL.jpg";
// String image3 = "https://images-cn.ssl-images-amazon.com/images/I/41fi2pEYkuL.jpg";
// @Override protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_main);
// top_img = (ImageView) findViewById(R.id.top_img);
// center_img = (ImageView) findViewById(R.id.center_img);
// bottom_img = (ImageView) findViewById(R.id.bottom_img);
// Picasso.with(this).load(image1).into(top_img);
// Picasso.with(this).load(image2).into(center_img);
// Picasso.with(this).load(image3).into(bottom_img);
// top_img.setOnClickListener(new View.OnClickListener() {
// @Override public void onClick(View view) {
// Intent intent = new Intent(MainActivity.this, ImageActivity.class);
// int[] screenLocation = new int[2];
// top_img.getLocationOnScreen(screenLocation);
// intent.putExtra(LEFT, screenLocation[0]).
// putExtra(TOP, screenLocation[1]).
// putExtra(WIDTH, top_img.getWidth()).
// putExtra(HEIGHT, top_img.getHeight()).
// putExtra(IMAGE, image1);
// startActivity(intent);
// 取消activity动画
// overridePendingTransition(0, 0);
// } });
// center_img.setOnClickListener(new View.OnClickListener() {
// @Override public void onClick(View view) {
// Intent intent = new Intent(MainActivity.this, ImageActivity.class);
// int[] screenLocation = new int[2];
// center_img.getLocationOnScreen(screenLocation);
// intent.putExtra(LEFT, screenLocation[0]).
// putExtra(TOP, screenLocation[1]).
// putExtra(WIDTH, center_img.getWidth()).
// putExtra(HEIGHT, center_img.getHeight()).
// putExtra(IMAGE, image2); startActivity(intent);
// 取消activity动画
// overridePendingTransition(0, 0);
// } });
// bottom_img.setOnClickListener(new View.OnClickListener() {
// @Override public void onClick(View view) {
// Intent intent = new Intent(MainActivity.this, ImageActivity.class);
// int[] screenLocation = new int[2];
// bottom_img.getLocationOnScreen(screenLocation);
// intent.putExtra(LEFT, screenLocation[0]).
// putExtra(TOP, screenLocation[1]).
// putExtra(WIDTH, bottom_img.getWidth()).
// putExtra(HEIGHT, bottom_img.getHeight()).
// putExtra(IMAGE, image3);
// startActivity(intent);
// 取消activity动画
// overridePendingTransition(0, 0); } }); }}


// 图片预览类ImageActivity.java package com.longsh.imageviewanimator;
// import android.animation.ObjectAnimator;
// import android.animation.TimeInterpolator;
// import android.app.Activity;
// import android.graphics.Color;
// import android.graphics.drawable.ColorDrawable;
// import android.os.Bundle;import android.view.View;
// import android.view.ViewTreeObserver;
// import android.view.animation.AccelerateInterpolator;
// import android.view.animation.DecelerateInterpolator;
// import android.widget.ImageView;
// import android.widget.LinearLayout;
// import com.squareup.picasso.Picasso;
// public class ImageActivity extends Activity {
// private static final int DURATION = 666;
// public final static String TOP = "top";
// public final static String LEFT = "left";
// public final static String WIDTH = "width";
// public final static String HEIGHT = "height";
// public final static String IMAGE = "image";
// private ImageView imageView;
// private int mLeftDelta;
// private int mTopDelta;
// private float mWidthScale;
// private float mHeightScale;
// private LinearLayout linearLayout;
// private ColorDrawable colorDrawable;
// private int intentTop;
// private int intentLeft;
// private int intentWidth;
// private int intentHeight;
// @Override protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_image);
// Bundle bundle = getIntent().getExtras();
// intentTop = bundle.getInt(TOP);
// intentLeft = bundle.getInt(LEFT);
// intentWidth = bundle.getInt(WIDTH);
// intentHeight = bundle.getInt(HEIGHT);
// String image = bundle.getString(IMAGE);
// imageView = (ImageView) findViewById(R.id.imageView);
// Picasso.with(this).load(image).into(imageView);
// linearLayout = (LinearLayout) findViewById(R.id.linear_bg);
// colorDrawable = new ColorDrawable(Color.BLACK);
// 布局背景设置
// linearLayout.setBackgroundDrawable(colorDrawable);
// ViewTreeObserver监听
// if (savedInstanceState == null) {
// ViewTreeObserver observer = imageView.getViewTreeObserver();
// observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
// @Override
// public boolean onPreDraw() {
// imageView.getViewTreeObserver().removeOnPreDrawListener(this);
// 坐标的获取设置
// int[] screenLocation = new int[2];
// imageView.getLocationOnScreen(screenLocation);
// mLeftDelta = intentLeft - screenLocation[0];
// mTopDelta = intentTop - screenLocation[1];
// mWidthScale = (float) intentWidth / imageView.getWidth();
// mHeightScale = (float) intentHeight / imageView.getHeight();
// 开启缩放动画
// enterAnimation(); return true; } }); }
// imageView.setOnClickListener(new View.OnClickListener() {
// @Override public void onClick(View view) {
// exitAnimation(new Runnable() {
// public void run() {
// finish();
// 取消activity动画
// overridePendingTransition(0, 0);
// } }); } }); }
// @Override
// public void onBackPressed() {
// exitAnimation(new Runnable() {
// public void run() { finish();
// 取消activity动画
// overridePendingTransition(0, 0);
// } }); }
// 进入动画
// public void enterAnimation() {
// 设置imageview动画的初始值
// imageView.setPivotX(0);
// imageView.setPivotY(0);
// imageView.setScaleX(mWidthScale);
// imageView.setScaleY(mHeightScale);
// imageView.setTranslationX(mLeftDelta);
// imageView.setTranslationY(mTopDelta);
// 设置动画
// TimeInterpolator sDecelerator = new DecelerateInterpolator();
// 设置imageview缩放动画,以及缩放开始位置
// imageView.animate().setDuration(DURATION).scaleX(1).scaleY(1). translationX(0).translationY(0).setInterpolator(sDecelerator);
// 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
// ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
// bgAnim.setDuration(DURATION);
// bgAnim.start(); }
// public void exitAnimation(final Runnable endAction) {
// TimeInterpolator sInterpolator = new AccelerateInterpolator();
// 设置imageview缩放动画,以及缩放结束位置
// imageView.animate().setDuration(DURATION).scaleX(mWidthScale).scaleY(mHeightScale).
// translationX(mLeftDelta).translationY(mTopDelta) .setInterpolator(sInterpolator).withEndAction(endAction);
// 设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
// ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
// bgAnim.setDuration(DURATION); bgAnim.start(); }}

    }


}
