package com.trj.tbase.module.filtermodule;

import java.util.List;

public interface TFilterModuleBuilder {

    /**
     * 设置默认标题1
     */
    void setDefaultTitle1(String defaultTitle1);

    /**
     * 设置默认标题2
     */
    void setDefaultTitle2(String defaultTitle2);

    /**
     * 设置默认标题3
     */
    void setDefaultTitle3(String defaultTitle3);

    /**
     * 设置默认标题4
     */
    void setDefaultTitle4(String defaultTitle4);

    /**
     * 设置是否可以更改默认标题
     */
    void isChangeDefaultTitle(boolean isChange);
    /**
     * 设置是否需要标记选中行
     */
    void setItemSelectMark(boolean itemSelectMark);

    /**
     * 设置集合1
     * @param list1
     */
    void setList1(List<String> list1);

    /**
     * 设置集合1
     * @param list1
     * @param defaultPosition 集合1的默认选择位置
     */
    void setList1(List<String> list1, int defaultPosition);

    /**
     * 设置集合2
     *
     * @param list2
     */
    void setList2(List<String> list2);

    /**
     * 设置集合2
     *
     * @param list2
     * @param defaultPosition 集合1的默认选择位置
     */
    void setList2(List<String> list2, int defaultPosition);

    /**
     * 设置集合3
     *
     * @param list3
     */
    void setList3(List<String> list3);

    /**
     * 设置集合3
     *
     * @param list3
     * @param defaultPosition 集合1的默认选择位置
     */
    void setList3(List<String> list3, int defaultPosition);
    /**
     * 设置集合4
     * @param list4
     */
    void setList4(List<String> list4);
    /**
     * 设置集合4
     * @param list4
     * @param defaultPosition 集合1的默认选择位置
     */
    void setList4(List<String> list4, int defaultPosition);

    /**
     * 隐藏筛选框
     */
    void hide();


}
