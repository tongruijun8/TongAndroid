package com.trj.tbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public abstract class TBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> tList;
    protected LayoutInflater inflater;

    protected String formatDatetime = "yyyy-MM-dd HH:mm:ss";
    protected String formatDatetime2 = "yyyy-MM-dd HH:mm";
    protected String formatDatetime3 = "yyyy-MM-dd";

    public TBaseAdapter(Context context, List<T> tList) {
        this.context = context;
        this.tList = tList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tList.size();
    }

    @Override
    public Object getItem(int position) {
        return tList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return bindView(position, convertView, parent);
    }

    //绑定数据
    public abstract View bindView(int position, View convertView, ViewGroup parent);


    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void updateItemView(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();
        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            if(updataItemView(view,position)){
                getView(position, view, listView);
            }
        }
    }


    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void updateItemViewX(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();
        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            if(updataItemView(view,position)){
                getView(position-1, view, listView);
            }
        }
    }


    protected boolean updataItemView(View view, int position){

        return true;
    }

}
