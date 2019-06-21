package com.trj.tbase.module.listmodule.xlistviewmodule;

/**
 * @author tong
 * @date 2018/5/3 12:33
 */
public interface TXListViewListenter{

    /**
     * 异常页面点击事件：1.可以处理数据异常的页面；2.还可以处理网络异常的页面
     */
    void exceptionPageClickEvent();

    /**
     * 获取列表数据
     */
    void getData(int page);



}
