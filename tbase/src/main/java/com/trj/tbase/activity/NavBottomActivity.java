package com.trj.tbase.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.trj.tbase.R;
import com.trj.tbase.adapter.TViewPagerStateAdapter;
import com.trj.tbase.fragment.TFragment;
import com.trj.tlib.views.TViewPager;

import java.util.ArrayList;
import java.util.List;

public abstract class NavBottomActivity extends InitActivity implements BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    protected BottomNavigationView mBottomNavView;
    protected TViewPager mViewpager;
    //ViewPager是否带滚动动画
    private boolean smoothScroll;

    //fragment数据集合
    protected List<TFragment> fragmentList = new ArrayList<>();

    private TViewPagerStateAdapter tViewPagerStateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bottom);
        initWork();
        initView();
    }

    @Override
    protected void initView() {

        smoothScroll = initSmoothScroll();

        mBottomNavView = findViewById(R.id.bottom_nav_view);
        mViewpager = findViewById(R.id.viewpager);
        mViewpager.setScanScroll(false);
        mViewpager.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mBottomNavView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

//        1.取消位移动画的效果
//        activityAssist.disableShiftMode(mBottomNavView);
//        2.取消导航栏的点击效果（类似水波纹的效果）：app:itemBackground="@null"
//        3.取消导航栏的每项点击文字和图片放大的效果：我们需要在values中的demens.xml中设置相应的值

        mBottomNavView.setOnNavigationItemSelectedListener(this);
        mBottomNavView.setOnNavigationItemReselectedListener(this);
        initFragmentData();
        //添加底部菜单
        int resMenuId = initTabMenu();
        if (resMenuId == 0) {
            //默认
            resMenuId = R.menu.navigation_bottom;
        }
        mBottomNavView.inflateMenu(resMenuId);
        tViewPagerStateAdapter = new TViewPagerStateAdapter(getSupportFragmentManager());
        tViewPagerStateAdapter.setmFragments(fragmentList);
        mViewpager.setAdapter(tViewPagerStateAdapter);
        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setScanScroll(false);
        mViewpager.addOnPageChangeListener(this);
    }

    protected abstract boolean initSmoothScroll();


    protected abstract void initFragmentData();
//    {
//        //默认
////        if (fragmentList == null || fragmentList.size() == 0) {
////            fragmentList.add(BlankFragment.newInstance("首页", ""));
////            fragmentList.add(BlankFragment.newInstance("订单", ""));
////            fragmentList.add(BlankFragment.newInstance("消息", ""));
////            fragmentList.add(BlankFragment.newInstance("我的", ""));
////        }
//    }

    protected abstract int initTabMenu();
//    {
//        return R.menu.navigation_bottom;
//    }

    protected boolean selectTabItem(int position){
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    protected MenuItem menuItem;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;
        int itemId = item.getItemId();
        int index = -1;
        if (itemId == R.id.nav_bottom_item1) {
            index = 0;

        } else if (itemId == R.id.nav_bottom_item2) {
            if (selectTabItem(1)) {
                fragmentList.get(1).initData();
                mViewpager.setCurrentItem(1, smoothScroll);
                return true;
            }
        } else if (itemId == R.id.nav_bottom_item3) {
            if (selectTabItem(2)) {
                fragmentList.get(2).initData();
                mViewpager.setCurrentItem(2, smoothScroll);
                return true;
            }
        } else if (itemId == R.id.nav_bottom_item4) {
            if (selectTabItem(3)) {
                fragmentList.get(3).initData();
                mViewpager.setCurrentItem(3, smoothScroll);
                return true;
            }
        }

        if (selectTabItem(index)) {
            fragmentList.get(index).initData();
            mViewpager.setCurrentItem(index, smoothScroll);
            return true;
        }

        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            mBottomNavView.getMenu().getItem(0).setChecked(false);
        }
        menuItem = mBottomNavView.getMenu().getItem(position);
        menuItem.setChecked(true);
        selectTabItem(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
