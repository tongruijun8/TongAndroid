package com.trj.tbase.module.filtermodule;

import android.content.Context;
import android.view.View;

import java.util.List;

public class TFilterTitle extends TFilterModule implements TFilterModuleBuilder {

    /**
     * 筛选框
     *
     * @param context
     * @param view    根View
     * @param columns 列数(目前最大支持4)
     *
     *                <p>
     *                备注：在使用的时候，需要的布局中添加layout_filter.xml 布局文件，且必须放在activity布局的最上层
     */
    public TFilterTitle(Context context, View view, int columns) {
        super(context, view, columns);
    }

    @Override
    public void setDefaultTitle1(String defaultTitle1) {
        this.defaultTitle1 = defaultTitle1;
        viewHolder.mLayoutFilterName1.setText(defaultTitle1);
    }

    @Override
    public void setDefaultTitle2(String defaultTitle2) {
        this.defaultTitle2 = defaultTitle2;
        viewHolder.mLayoutFilterName2.setText(defaultTitle2);
    }

    @Override
    public void setDefaultTitle3(String defaultTitle3) {
        this.defaultTitle3 = defaultTitle3;
        viewHolder.mLayoutFilterName3.setText(defaultTitle3);
    }

    @Override
    public void setDefaultTitle4(String defaultTitle4) {
        this.defaultTitle4 = defaultTitle4;
        viewHolder.mLayoutFilterName4.setText(defaultTitle4);
    }

    /**
     * 用来设置是否改变筛选标题：即根据选择的条目来动态显示条目的内容
     *
     * @param isChange 默认false 不改变，则显示默认标题
     */
    @Override
    public void isChangeDefaultTitle(boolean isChange) {
        isChangeTitleStr = isChange;
    }


    /**
     * 设置是否需要标记选中行：默认标记（true）
     *
     * @param itemSelectMark
     */
    @Override
    public void setItemSelectMark(boolean itemSelectMark) {
        this.itemSelectMark = itemSelectMark;
    }


    /**
     * 设置筛选集合1
     *
     * @param list1
     */
    @Override
    public void setList1(List<String> list1) {
        setList1(list1, 0);
    }

    @Override
    public void setList1(List<String> list1, int defaultPosition) {
        this.list1 = list1;
        this.listPosition1 = defaultPosition;
        if (null != list1 && list1.size() > 0) {
            oneShow = true;
            viewHolder.mLayoutFilterImg1.setVisibility(View.VISIBLE);
            if (listPosition1 > 0) {
                viewHolder.mLayoutFilterName1.setText(list1.get(listPosition1));
            }else{
                if (defaultTitle1 == null || defaultTitle1.equals("")) {
                    viewHolder.mLayoutFilterName1.setText(list1.get(0));
                }
            }
        } else {
            viewHolder.mLayoutFilterImg1.setVisibility(View.GONE);
        }
    }

    /**
     * 设置筛选集合2
     *
     * @param list2
     */
    @Override
    public void setList2(List<String> list2) {
        setList2(list2, 0);
    }

    @Override
    public void setList2(List<String> list2, int defaultPosition) {
        this.list2 = list2;
        this.listPosition2 = defaultPosition;
        if (null != list2 && list2.size() > 0) {
            twoShow = true;
            viewHolder.mLayoutFilterImg2.setVisibility(View.VISIBLE);
            if (listPosition2 > 0) {
                viewHolder.mLayoutFilterName2.setText(list2.get(listPosition2));
            }else{
                if (defaultTitle2 == null || defaultTitle2.equals("")) {
                    viewHolder.mLayoutFilterName2.setText(list2.get(0));
                }
            }
        } else {
            viewHolder.mLayoutFilterImg2.setVisibility(View.GONE);
        }
    }

    /**
     * 设置筛选集合3
     *
     * @param list3
     */
    @Override
    public void setList3(List<String> list3) {
        setList3(list3,0);
    }

    @Override
    public void setList3(List<String> list3, int defaultPosition) {
        this.list3 = list3;
        this.listPosition3 = defaultPosition;
        if (null != list3 && list3.size() > 0) {
            threeShow = true;
            viewHolder.mLayoutFilterImg3.setVisibility(View.VISIBLE);
            if (listPosition3 > 0) {
                viewHolder.mLayoutFilterName3.setText(list3.get(listPosition3));
            }else{
                if (defaultTitle3 == null || defaultTitle3.equals("")) {
                    viewHolder.mLayoutFilterName3.setText(list3.get(0));
                }
            }
        } else {
            viewHolder.mLayoutFilterImg3.setVisibility(View.GONE);
        }
    }

    /**
     * 设置筛选集合4
     *
     * @param list4
     */
    @Override
    public void setList4(List<String> list4) {
        setList4(list4, 0);
    }

    @Override
    public void setList4(List<String> list4, int defaultPosition) {
        this.list4 = list4;
        this.listPosition4 = defaultPosition;
        if (null != list4 && list4.size() > 0) {
            fourShow = true;
            viewHolder.mLayoutFilterImg4.setVisibility(View.VISIBLE);
            if (listPosition4 > 0) {
                viewHolder.mLayoutFilterName4.setText(list4.get(listPosition4));
            }else{
                if (defaultTitle4 == null || defaultTitle4.equals("")) {
                    viewHolder.mLayoutFilterName4.setText(list4.get(0));
                }
            }
        } else {
            viewHolder.mLayoutFilterImg4.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏筛选框
     */
    @Override
    public void hide() {
        hideSsk();
    }

}
