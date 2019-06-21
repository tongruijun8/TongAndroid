package com.trj.tbase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */

public class TViewPagerStateAdapter<T> extends FragmentStatePagerAdapter {

//    private Context context;
    private List<T> mFragments;
    private List<String> mTitleList;

    public TViewPagerStateAdapter(FragmentManager fm) {
        super(fm);
    }

    public TViewPagerStateAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        mFragments = fragments;
    }

    public TViewPagerStateAdapter(FragmentManager fm, List<T> fragments, List<String> titleList) {
        super(fm);
        mFragments = fragments;
        mTitleList = titleList;
    }

    public void setmFragments(List<T> mFragments) {
        this.mFragments = mFragments;
    }

    public void setmTitleList(List<String> mTitleList) {
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList==null ? "": mTitleList.get(position);
    }

}
