package com.trj.tbase.module.listmodule;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：小童
 * 创建时间：2019/6/12 15:36
 * <p>
 * 列表模块的基类
 */
public abstract class TListModule {

    /**
     * 加载刷新时间
     */
    public static final int TIME_LR = 2000;

    /* 每页大小 */
    protected int pageSize = 10;

    protected int page = 1;
    /**
     * 是否可以点击默认页面:默认不能点击
     */
    protected boolean clickDefaultPage = false;

    /**
     * 当前页面状态：0.正常；1.默认页面；2.异常页面
     */
    protected int state = 0;

    //默认布局的控件
    private RelativeLayout defRl;
    private ImageView defImg;
    private TextView defText;

    private String defTextStr = "";

    /**
     * 设置默认布局的图片
     *
     * @param resDrawable
     */
    public void setDefImg(@DrawableRes int resDrawable) {
        defImg.setImageResource(resDrawable);
    }

    /**
     * 设置默认布局的文本
     *
     * @param defTextStr
     */
    public void setDefText(String defTextStr) {
        if (defTextStr != null && !defTextStr.equals("")) {
            this.defTextStr = defTextStr;
            defText.setText(defTextStr);
            setDefTextViewVisibility(View.VISIBLE);
        }
    }

    /**
     * Set the visibility state of this view.
     *
     * @param visibility One of {View.VISIBLE,View.INVISIBLE,or View.GONE.}
     * @attr ref android.R.styleable#View_visibility
     */
    public void setDefTextViewVisibility(int visibility) {
        defText.setVisibility(visibility);
    }

    public TListModule(View rootView) {
        setPageSize(10);//默认值
        setPage(1);
        initView(rootView);
    }

    public void initView(View rootView) {
        defRl = rootView.findViewById(R.id.layout_default_all);
        defImg = rootView.findViewById(R.id.layout_default_img);
        defText = rootView.findViewById(R.id.layout_default_text);
        isShowListLayout(false);
        defRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clickDefaultPage) {
                    return;
                }
                defRl.setVisibility(View.GONE);
                isShowListLayout(false);
                if (state == 2) {//点击异常页面
                    state = 0;
                    if (null != listenter) {
                        listenter.exceptionPageClickEvent();
                    }
                } else if (state == 1) {
                    state = 0;
                    if (null != listenter) {
                        listenter.getData(page);
                    }
                }
            }
        });
    }

    protected TListListenter listenter;

    public void setTListListenter(TListListenter listenter) {
        this.listenter = listenter;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        //最小10
        if (pageSize < 10) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    /**
     * 是否显示列表控件
     *
     * @param isShow
     */
    public abstract void isShowListLayout(boolean isShow);

    /**
     * 是否显示默认布局
     *
     * @param isShow
     */
    public void isShowDefLayout(boolean isShow) {
        if (page != 1) {
            return;
        }
        if (isShow) {
            state = 1;
            defRl.setVisibility(View.VISIBLE);
        } else {
            defRl.setVisibility(View.GONE);
        }
    }

    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 请求异常方法
     */
    public void error() {
        if (page == 1) {
            state = 2;
            setDefImg(R.mipmap.default_list);
            isShowDefLayout(true);
        }
    }

    /**
     * 设置是否可以点击默认页面重新加载
     *
     * @param clickDefaultPage
     */
    public void setClickDefaultPage(boolean clickDefaultPage) {
        this.clickDefaultPage = clickDefaultPage;
    }

}
