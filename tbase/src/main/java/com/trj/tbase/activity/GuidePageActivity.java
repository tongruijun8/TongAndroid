package com.trj.tbase.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tbase.adapter.ImagePagerAdapter;
import com.trj.tlib.uils.GlideUtile;

import java.util.ArrayList;
import java.util.List;

public abstract class GuidePageActivity extends BaseActivity {

    private ViewPager viewPager;
    private TextView skipView;

    private List<Object> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
    }

    @Override
    protected void initView() {
        super.initView();
        viewPager = findViewById(R.id.viewpager);
        skipView = findViewById(R.id.skip);

        imgList.clear();
        if (!setPageData(imgList)) {
            imgList.add(R.mipmap.page1);
            imgList.add(R.mipmap.page2);
            imgList.add(R.mipmap.page3);
        }

        List<View> viewList = new ArrayList<>();
        if (imgList.size() > 0) {
            for (int i = 0; i < imgList.size(); i++) {
                viewList.add(getPageView(imgList.get(i)));
            }
        }

        ImagePagerAdapter adapter = new ImagePagerAdapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == imgList.size() - 1) {
                    skipView.setVisibility(View.VISIBLE);
                } else {
                    skipView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        skipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSkipView();
                finish();
            }
        });


    }

    private View getPageView(Object path) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
        ImageView imageView = view.findViewById(R.id.item_image_imageview);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtile.bindImageView(context,path, imageView);
        return view;
    }

    public abstract boolean setPageData(List<Object> imgList);

    protected abstract void onClickSkipView();

}
