package com.trj.demo2.activity.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trj.demo2.BR;
import com.trj.demo2.R;
import com.trj.demo2.base.DMVPFragment;
import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.databinding.FragmentMainTwoBinding;
import com.trj.demo2.http.DemoModel;
import com.trj.demo2.testmvp.recyclerview.TestBaseViewHolder;
import com.trj.demo2.testmvp.recyclerview.TestRecyclerAdapter;
import com.trj.tlib.uils.PermissionRxUtil;
import com.trj.tlib.uils.ToastUtil;

import java.util.List;

/**
 * 数据库的用户数据：MVP+Databing
 */
public class MainTwoFragment extends DMVPFragment<TwoFmView,TwoFmPresenter> implements TwoFmView {

    private FragmentMainTwoBinding fragmentBinding;

    private int page = 1;
    private int PAGE_SIZE = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_two, container, false);
        initFragmentView();
        return fragmentBinding.getRoot();
    }

    @Override
    protected TwoFmPresenter initPersenter() {
        return new TwoFmPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    private PermissionRxUtil permissionRxUtil;
    private TestRecyclerAdapter adapter;

    protected void initFragmentView() {
        permissionRxUtil = new PermissionRxUtil(this);

        int colors[] = {
                Color.rgb(47, 223, 189),
                Color.rgb(223, 47, 189),
                Color.rgb(189, 223, 47),
                Color.rgb(47, 55, 80)
        };

        fragmentBinding.swiperefreshlayout.setColorSchemeColors(colors);
        fragmentBinding.swiperefreshlayout.setOnRefreshListener(() -> {
            page = 1;
            getData();
        });

        fragmentBinding.twoRecyclerview.setLayoutManager(new LinearLayoutManager(activity.context));

        initAdapter();
        fragmentBinding.swiperefreshlayout.setRefreshing(true);
        page = 1;
        getData();

    }

    private void getData() {
        getPresenter().getListData(page, PAGE_SIZE);
    }

    private void initAdapter() {

        final int variableId = BR.user;

        adapter = new TestRecyclerAdapter<Users>(R.layout.item_recycler, null) {
            @Override
            protected void convert(TestBaseViewHolder helper, Users item) {
                helper.setBinding(variableId, item);
            }
        };
        adapter.setOnLoadMoreListener(() -> {
            page++;
            getData();
        }, fragmentBinding.twoRecyclerview);
        //添加条目点击事件
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtil.showToast(activity.context, "点击了" + position);
            Users users = (Users) adapter.getData().get(position);
            users.setUser_name("after change test");
//                adapter.notifyItemChanged(position);
//                adapter.getData().remove(position);
//                adapter.notifyItemRemoved(position);
//                adapter.notifyDataSetChanged();

//                mData.add("新增");
//                homeAdapter.notifyItemInserted(pos);

        });

        adapter.openLoadAnimation();
//        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);

        fragmentBinding.twoRecyclerview.setAdapter(adapter);
    }


    @Override
    public void bindListData(PageBean<Users> pageBean) {
        if (pageBean == null) {
            return;
        }
        List<Users> usersList = pageBean.getList();
        final int size = usersList == null ? 0 : usersList.size();
        if (page == 1) {
            adapter.setNewData(usersList);
        } else {
            if (size > 0) {
                adapter.addData(usersList);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        if(code == 1){
            fragmentBinding.swiperefreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void onDestroy() {
        permissionRxUtil.onDestroy();
        super.onDestroy();
    }


}
