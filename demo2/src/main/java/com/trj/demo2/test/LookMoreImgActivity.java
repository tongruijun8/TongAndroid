package com.trj.demo2.test;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tbase.adapter.ImagePagerAdapter;
import com.trj.tbase.module.titlemodule.TitleModule;
import com.trj.tbase.tdialog.BaseDialog;
import com.trj.tbase.tdialog.TSelectBottomDialog;
import com.trj.tlib.assist.ImgPaths;
import com.trj.tlib.uils.BitmapUtils;
import com.trj.tlib.uils.GlideUtile;
import com.trj.tlib.uils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class LookMoreImgActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_more_img);
        setStateBar(false);
    }

    private ViewPager viewPager;
    private TextView textView;

    private List<String> dataList;
    private List<View> viewList = new ArrayList<>();
    private int index = 0;

    private WallpaperManager wManager = null;   //定义WallpaperManager服务

    @Override
    protected void initView() {
        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
        titleModule.initTitle("", true);
        titleModule.setTitleBackground(R.color.color_transparent_half);
        titleModule.setTitleLeftTextColor(R.color.color_white);
        titleModule.initTitleMenu(TitleModule.MENU_IMAGE);
        titleModule.setTitleMenuImage(R.mipmap.caidan);
        titleModule.setTitleTopViewShow(false);

        viewPager = findViewById(R.id.td_look_viewpager);
        textView = findViewById(R.id.bottom_text);

        titleModule.showTitleLayout(false);//隐藏
        taggleBottomView();

        wManager = WallpaperManager.getInstance(this);

        dataList = Arrays.asList(ImgPaths.path);
        for (int i = 0; i < dataList.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            ImageView imageView = view.findViewById(R.id.item_image_imageview);
            String path = dataList.get(i);
            GlideUtile.bindImageView(context, path, imageView);
            viewList.add(view);
            view.setOnClickListener(v -> {
                titleModule.taggleTitleLayout();
                taggleBottomView();
            });

            view.setOnLongClickListener(v -> {
                onLongClickImgView(path);
                return false;
            });

        }
        titleModule.setTitleLeftText("1/" + dataList.size());
        ImagePagerAdapter adapter = new ImagePagerAdapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(index, false);
        textView.setText(dataList.get(index));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
                titleModule.setTitleLeftText((i + 1) + "/" + dataList.size());
                textView.setText(dataList.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private boolean isShow;
    //改变标题栏的 隐藏 和 显示 状态
    public void taggleBottomView() {
        if (isShow) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);//隐藏
        }
        isShow = !isShow;
    }

    @Override
    public void onClickMenu(View view) {
        super.onClickMenu(view);
        skipActivity(LookBigImgActivity.class);
    }


    private void onLongClickImgView(String path){
        new TSelectBottomDialog.Builder(context)
                .setText("设为壁纸")
                .setOnTdSelect(position -> {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                wManager.setBitmap(BitmapUtils.urlToBitmap(path));
                                LookMoreImgActivity.this.runOnUiThread(() -> ToastUtil.showToast(context,"设置成功"));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                })
                .create()
                .show(getSupportFragmentManager(), "set_bizhi");
    }

}
