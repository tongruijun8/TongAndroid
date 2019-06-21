package com.trj.tlib.bean;

/**
 * 作者：小童
 * 创建时间：2019/5/7 14:29
 *
 * SidebarView2 的数据对象，
 *
 *
 */
public class SidebarInfo {

    /**
     * 名字：索引的名字
     */
    private String sidebarName;
    /**
     * 位置：用于保存定位列表的位置
     */
    private int sidebarPosition;

    public String getSidebarName() {
        return sidebarName;
    }

    public void setSidebarName(String sidebarName) {
        this.sidebarName = sidebarName;
    }

    public int getSidebarPosition() {
        return sidebarPosition;
    }

    public void setSidebarPosition(int sidebarPosition) {
        this.sidebarPosition = sidebarPosition;
    }
}
