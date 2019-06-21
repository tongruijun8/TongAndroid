package com.trj.tbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.trj.tbase.R;
import com.trj.tbase.adapter.ImagePagerAdapter;
import com.trj.tlib.uils.GlideUtile;

import java.util.ArrayList;
import java.util.List;


/**
 * 查看图片
 */
public class LookImages2Activity extends BaseActivity {

    private int index;
    private ArrayList<String> dataImg;

    private List<View> viewList = new ArrayList<>();
    private ViewPager viewPager;

    private ImagePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_images);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("", true);
        viewPager = (ViewPager) findViewById(R.id.look_image_viewpager);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        dataImg = intent.getStringArrayListExtra("list");
        addImageViews();
        showImages();

    }

    private void addImageViews(){
        int len = dataImg.size();
        for (int i = 0; i < len; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            ImageView imageView = view.findViewById(R.id.item_image_imageview);
            GlideUtile.bindImageView(context,dataImg.get(i),imageView);
            viewList.add(view);
        }
    }

    private void showImages(){
        if (null != dataImg && dataImg.size() > 0) {
            if (index >= 0) {
                titleModule.setTitleLeftText((index+1) + "/" + dataImg.size());
                adapter = new ImagePagerAdapter(viewList);
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(index,false);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        titleModule.setTitleLeftText((position+1) + "/" + dataImg.size());
                        index = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        }
    }

}
