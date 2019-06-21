package com.trj.demo2.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tlib.assist.ImgPaths;
import com.trj.tlib.uils.GlideUtile;

public class TestRoundImageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_round_image_view);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试带圆角的图片", true);

        GlideUtile.bindImageView(context, ImgPaths.path[0], (ImageView) findViewById(R.id.roundimg1));
        GlideUtile.bindImageView(context, ImgPaths.path[1], (ImageView) findViewById(R.id.roundimg2));
        GlideUtile.bindImageView(context, ImgPaths.path[2], (ImageView) findViewById(R.id.roundimg3));
        GlideUtile.bindImageView(context, ImgPaths.path[3], (ImageView) findViewById(R.id.roundimg4));
        GlideUtile.bindImageView(context, ImgPaths.path[4], (ImageView) findViewById(R.id.roundimg5));

        GlideUtile.bindImageViewRound(context, ImgPaths.path[0], (ImageView) findViewById(R.id.img5));
        GlideUtile.bindImageViewC(context, ImgPaths.path[1], 10, (ImageView) findViewById(R.id.img6));
        GlideUtile.bindImageViewC(context, ImgPaths.path[2], 20, (ImageView) findViewById(R.id.img7));
        GlideUtile.bindImageViewC(context, ImgPaths.path[3], 30, (ImageView) findViewById(R.id.img8));
        GlideUtile.bindImageViewC(context, ImgPaths.path[4], 40, (ImageView) findViewById(R.id.img9));

    }
}
