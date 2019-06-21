package com.trj.tbase.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.trj.tbase.R;
import com.trj.tbase.module.titlemodule.TitleListenter;
import com.trj.tbase.module.titlemodule.TitleModule;
import com.trj.tlib.uils.Logger;

/**
 *
 * 建议项目 Activity 写一个父类，继承BaseActivity，用于初始化的处理
 *
 * 注：1.此Base 封装了一个跟布局（layout_root.xml），继承的activity只需要添加子布局（内容布局）
 *     2.跟布局中也添加了标题的布局，使用方式如下：
 *
 *     实现此方法
 *         @Override
 *     protected void initView() {
 *         super.initView();//此行为初始化 TitleModule 模块（用于初始化标题栏），如不需要标题栏可以删除此行
 *     }
 *
 *     TitleListenter 此为标题栏的回调接口，用于实现标题栏的触发事件
 *
 *     3.此基类不支持使用 Data Binding库
 *
 * @author tong
 * @date 2018/3/16 15:52
 */
public class BaseActivity extends InitActivity implements TitleListenter {

    public View rootMask;

    protected ViewStub rootTitleViewStub;
    private ViewStub rootContentViewStub;

    protected TitleModule titleModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_root);
        rootTitleViewStub = findViewById(R.id.root_title_viewstub);
        rootContentViewStub = findViewById(R.id.root_content_viewstub);
        rootMask = findViewById(R.id.root_mask);
        initWork();
//        添加内容View
        initContentView(layoutResID);
        initView();
    }

    /**
     * 初始化标题布局(TitleModule)
     */
    private void initTitleView() {
        rootTitleViewStub.setLayoutResource(R.layout.ttitle);
        rootTitleViewStub.inflate();
    }

    /**
     * 初始化内容布局
     *
     * @param layoutResID
     */
    private void initContentView(int layoutResID) {
        rootContentViewStub.setLayoutResource(layoutResID);
        rootContentViewStub.inflate();
    }

    protected void initView() {
        Logger.t("-------initView--------2");
        initTitleView();
        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
    }


    //-------------------------TitleModule 中标题相关的点击事件的方法----------------------------
    @Override
    public void onClickBack(View view) {
        finish();
    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {

    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }

}
