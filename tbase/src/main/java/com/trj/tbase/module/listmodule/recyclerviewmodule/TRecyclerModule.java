package com.trj.tbase.module.listmodule.recyclerviewmodule;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trj.tbase.R;
import com.trj.tbase.module.listmodule.TBindDataListenter;
import com.trj.tbase.module.listmodule.TListModule;
import com.trj.tbase.module.listmodule.xlistviewmodule.TXBindDataListenter;
import com.trj.tbase.module.listmodule.xlistviewmodule.XListView;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import java.util.List;

/**
 * @author tong
 * @date 2018/5/3 12:13
 * RecyclerView 模块化
 *
 * 待研究
 *
 */
public abstract class TRecyclerModule<B> extends TListModule {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private boolean isShowRefresh = true;

    public TRecyclerModule(View rootView) {
        super(rootView);
        setPageSize(10);//默认值
        setPage(1);
        initView(rootView);
    }

    public void initView(View rootView,boolean isRefresh) {
        super.initView(rootView);
        swipeRefreshLayout = rootView.findViewById(R.id.swiperefreshlayout);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        initSwipeRefreshLayout();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (null != listenter) {
                    listenter.getData(page);
                }
            }
        });
        swipeRefreshLayout.setRefreshing(isRefresh);

    }

    /**
     * 获取 RecyclerView 控件
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 初始化刷新设置
     */
    private void initSwipeRefreshLayout() {
        if (initSwipeRefresh()) {
            int colors[] = {
                    Color.rgb(47, 223, 189),
                    Color.rgb(223, 47, 189),
                    Color.rgb(189, 223, 47),
                    Color.rgb(47, 55, 80)
            };
            swipeRefreshLayout.setColorSchemeColors(colors);
        }
    }

    /**
     * 是否显示XListView
     *
     * @param isShow
     */
    @Override
    public void isShowListLayout(boolean isShow) {
        if (isShow) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化刷新按钮的样式
     * @return
     */
    public abstract boolean initSwipeRefresh();

    /**
     * 初始化布局管理
     */
    public abstract void initLayoutManager();

    /**
     * 创建适配器
     */
    public abstract void createAdapter();


    public <A extends TRecyclerAdapter> void initAdapter(A adapter , final TBindDataListenter bindDataListenter) {

//        adapter = new TestRecyclerAdapter<B>(R.layout.item_recycler, null) {
//            @Override
//            protected void convert(TestBaseViewHolder helper, Users item) {
//                helper.setBinding(variableId, item);
//            }
//        };

        if (bindDataListenter != null) {
            bindDataListenter.createAdapterAndBindListData();
        }else{
            return;
        }

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (listenter != null) {
                    listenter.getData(page);
                }
            }
        }, recyclerView);
        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                B bean = (B) adapter.getData().get(position);

//                if (bindDataListenter != null) {
//                    bindDataListenter.onItemClick(bean, position);
//                }

//                adapter.notifyItemChanged(position);
//                adapter.getData().remove(position);
//                adapter.notifyItemRemoved(position);
//                adapter.notifyDataSetChanged();

//                mData.add("新增");
//                homeAdapter.notifyItemInserted(pos);
            }
        });

        adapter.openLoadAnimation();
//        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);

        recyclerView.setAdapter(adapter);
    }


}
