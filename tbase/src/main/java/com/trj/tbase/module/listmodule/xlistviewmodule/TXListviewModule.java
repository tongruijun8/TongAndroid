package com.trj.tbase.module.listmodule.xlistviewmodule;

import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tlib.uils.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author tong
 * @date 2018/5/3 12:13
 * XListView 模块化
 * M 代表返回值的类型
 *
 *
 * 为了配合RecyclerviewModule 推荐使用TXListviewModule2
 *
 */
public class TXListviewModule implements XListView.IXListViewListener {
    /**
     * 加载刷新时间
     */
    public static final int TIME_LR = 2000;

    /* 每页大小 */
    private int pageSize = 10;

    private ProgressBar mProgressBar;
    private XListView xListView;
    private Handler mHandler;
    private int page = 1;
    /**
     * 是否可以点击默认页面:默认不能点击
     */
    private boolean clickDefaultPage = false;

    /**
     * 当前页面状态：0.正常；1.默认页面；2.异常页面
     */
    private int state = 0;

    private boolean openRefresh, openLoad;

    //默认布局的控件
    private RelativeLayout defRl;
    private ImageView defImg;
    private TextView defText;

    private String defTextStr = "";
    /**
     * 设置默认布局的图片
     * @param resDrawable
     */
    public void setDefImg(@DrawableRes int resDrawable) {
        defImg.setImageResource(resDrawable);
    }
    /**
     * 设置默认布局的文本
     * @param defTextStr
     */
    public void setDefText(String defTextStr) {
        if(defTextStr!=null && !defTextStr.equals("")){
            this.defTextStr = defTextStr;
            defText.setText(defTextStr);
            setDefTextViewVisibility(View.VISIBLE);
        }
    }

    /**
     * Set the visibility state of this view.
     *
     * @param visibility One of {View.VISIBLE,View.INVISIBLE,or View.GONE.}
     *
     * @attr ref android.R.styleable#View_visibility
     */
    public void setDefTextViewVisibility(int visibility){
        defText.setVisibility(visibility);
    }

    public TXListviewModule(View rootView) {
        this(rootView,true,true);
    }

    public TXListviewModule(View rootView, boolean openRefresh, boolean openLoad) {
        setPageSize(10);//默认值
        setPage(1);
        initView(rootView,openRefresh,openLoad);
        isShowListView(false);
    }

    public void initView(View rootView, boolean openRefresh, boolean openLoad) {
        this.openRefresh = openRefresh;
        this.openLoad = openLoad;

        xListView = rootView.findViewById(R.id.xlistview);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        defRl = rootView.findViewById(R.id.layout_default_all);
        defImg = rootView.findViewById(R.id.layout_default_img);
        defText = rootView.findViewById(R.id.layout_default_text);
        initSet(xListView);
        defRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clickDefaultPage){
                    return;
                }
                defRl.setVisibility(View.GONE);
                isShowListView(false);
                if (state == 2){//点击异常页面
                    state = 0;
                    if (null != listenter) {
                        listenter.exceptionPageClickEvent();
                    }
                }else if(state == 1){
                    state = 0;
                    if (null != listenter) {
                        listenter.getData(page);
                    }
                }
            }
        });
    }

    private TXListViewListenter listenter;

    public void setTXListViewListenter(TXListViewListenter listenter) {
        this.listenter = listenter;
    }

    /**
     * 获取XListView 控件
     *
     * @return
     */
    public XListView getxListView() {
        return xListView;
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

    private void initSet(XListView xListView) {
        xListView.setPullRefreshEnable(openRefresh);
        xListView.setPullLoadEnable(openLoad);
        xListView.setAutoLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.setRefreshTime(getTime());
        mHandler = new Handler();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                if (null != listenter) {
                    listenter.getData(page);
                }
            }
        }, TIME_LR);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                if (null != listenter) {
                    listenter.getData(page);
                }
            }
        }, TIME_LR);
    }

    public void onLoad(int pageCount) {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime(getTime());
        if (pageCount > page) {
            xListView.setPullRefreshEnable(openRefresh);
            xListView.setPullLoadEnable(openLoad);
            xListView.setAutoLoadEnable(true);
        } else {
            xListView.setPullLoadEnable(false);
            xListView.setAutoLoadEnable(false);
        }
    }

    /**
     * 是否显示XListView
     *
     * @param isShow
     */
    public void isShowListView(boolean isShow) {
        if (isShow) {
            mProgressBar.setVisibility(View.GONE);
            xListView.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            xListView.setVisibility(View.GONE);
        }
    }

    public void isShowDefLayout(boolean isShow){
        if(page != 1){
            return;
        }
        if(isShow){
            state = 1;
            defRl.setVisibility(View.VISIBLE);
        }else{
            defRl.setVisibility(View.GONE);
        }
    }

    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 请求异常方法
     */
    public void error(){
        if(page == 1){
            state = 2;
            setDefImg(R.mipmap.default_list);
            isShowDefLayout(true);
        }else{
            if(page >1){
                page--;
                onLoad(1000);
            }
        }
    }

    /**
     * 设置是否可以点击默认页面重新加载
     * @param clickDefaultPage
     */
    public void setClickDefaultPage(boolean clickDefaultPage) {
        this.clickDefaultPage = clickDefaultPage;
    }

    /**
     *  绑定 XListView 的数据
     * @param infoList 请求数据
     * @param totalList  整体的数据集合
     * @param bindDataListenter 绑定数据的接口
     */
    public void bindXListViewData(List infoList,List totalList, TXBindDataListenter bindDataListenter) {
        if (getPage() == 1) {
            if (null == infoList || infoList.size() == 0) {
                onLoad(0);
                isShowDefLayout(true);
                return;
            }
            totalList.clear();
            isShowDefLayout(false);
            isShowListView(true);
            getxListView().setSelectionAfterHeaderView();
            getxListView().smoothScrollToPosition(0);
        }
        if (infoList != null) {
            totalList.addAll(infoList);
        }
        if (null != bindDataListenter) {
            bindDataListenter.createAdapterAndBindListData();
        }else{
            //提示用，可以忽略
            Logger.t("您还未创建或者关联适配器，请检查");
        }
        if (infoList == null || pageSize > infoList.size()) {
            onLoad(getPage());
        } else {
            onLoad(getPage() + 1);
        }
    }


}
