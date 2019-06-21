package com.trj.demo2.testmvp.recyclerview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trj.demo2.BR;
import com.trj.demo2.R;
import com.trj.demo2.base.DMVPActivity;
import com.trj.demo2.bean.Users;
import com.trj.demo2.databinding.ActivityTestRecyclerviewBinding;
import com.trj.demo2.http.DemoModel;
import com.trj.tbase.module.titlemodule.TitleModule;
import com.trj.tlib.uils.ToastUtil;

import java.util.List;

public class TestRecyclerviewActivity extends DMVPActivity<TestRecyclerView,TestRecyclerPresenter>
        implements TestRecyclerView {

    private ActivityTestRecyclerviewBinding binding;

    private int page = 1;
    private static final int PAGE_SIZE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_recyclerview);
        initView();
    }

    @Override
    protected TestRecyclerPresenter initPersenter() {
        return new TestRecyclerPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    @Override
    protected void initView() {
        TitleModule titleModule = new TitleModule(context, rootView);
        titleModule.initTitle("测试列表", true);
        titleModule.setListenter(this);
        int colors[] = {
                Color.rgb(47, 223, 189),
                Color.rgb(223, 47, 189),
                Color.rgb(189, 223, 47),
                Color.rgb(47, 55, 80)
        };

        binding.swiperefreshlayout.setColorSchemeColors(colors);
        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }
        });

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(context));

        initAdapter();
        binding.swiperefreshlayout.setRefreshing(true);
        page = 1;
        getData();

    }


    private void initAdapter() {

        final int variableId = BR.user;

        adapter = new TestRecyclerAdapter<Users>(R.layout.item_recycler, null) {
            @Override
            protected void convert(TestBaseViewHolder helper, Users item) {
                helper.setBinding(variableId, item);
            }
        };
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, binding.recyclerview);
        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showToast(context, "点击了" + position);
                Users users = (Users) adapter.getData().get(position);
                users.setUser_name("after change test");
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

        binding.recyclerview.setAdapter(adapter);
    }


    private void getData(){
        getPresenter().getListData(page, PAGE_SIZE);
    }

    private TestRecyclerAdapter adapter;

    @Override
    public void bindListData(List<Users> usersList) {

        final int size = usersList == null ? 0 : usersList.size();
        if (page == 1) {
            adapter.setNewData(usersList);
        } else {
            if (size > 0) {
                adapter.addData(usersList);
            }
        }
        if (size < PAGE_SIZE) {
            //一页如果不够pageSize就不显示没有更多数据布局
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }

    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        if(code == 1){
            binding.swiperefreshlayout.setRefreshing(false);
        }
    }

}
