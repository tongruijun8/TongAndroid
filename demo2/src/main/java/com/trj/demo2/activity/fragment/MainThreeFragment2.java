package com.trj.demo2.activity.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trj.demo2.BR;
import com.trj.demo2.R;
import com.trj.demo2.base.TestBean;
import com.trj.demo2.test.TestHandlerActivity;
import com.trj.demo2.test.TestTDialogActivity;
import com.trj.demo2.testmvp.TestMVPActivity;
import com.trj.demo2.testmvp.recyclerview.TestBaseViewHolder;
import com.trj.demo2.testmvp.recyclerview.TestRecyclerAdapter;
import com.trj.tbase.fragment.TInitFragment;

import java.util.ArrayList;
import java.util.List;


public class MainThreeFragment2 extends TInitFragment {

    private RecyclerView recyclerView;
    private TextView fmTitleView;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_two2;
    }

    List<TestBean> itemList = new ArrayList<>();
    String[] testNameStr = new String[]{
            "测试TDialog",
            "测试Handler",
            "测试MVP+"
    };
    Class<?>[] clazzArray = new Class[]{
            TestTDialogActivity.class,
            TestHandlerActivity.class,
            TestMVPActivity.class
    };

    protected void initFragmentView(View view) {
        fmTitleView = view.findViewById(R.id.fm_title);
        fmTitleView.setText("测试");
        recyclerView = view.findViewById(R.id.two_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity.context));
        for (int i = 0; i < testNameStr.length; i++) {
            TestBean testBean = new TestBean();
            testBean.setName(testNameStr[i]);
            testBean.setClazz(clazzArray[i]);
            itemList.add(testBean);
        }
        initAdapter();
    }

    @Override
    public void initData() {
        super.initData();

    }


    private void initAdapter() {

        final int variableId = BR.test;

        TestRecyclerAdapter adapter = new TestRecyclerAdapter<TestBean>(R.layout.item_test_three, itemList) {
            @Override
            protected void convert(TestBaseViewHolder helper, TestBean item) {
                helper.setBinding(variableId, item);
            }
        };
        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final TestBean testBean = (TestBean) adapter.getData().get(position);
                activity.skipActivity(testBean.getClazz());
            }
        });

        adapter.openLoadAnimation();
//        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);

        recyclerView.setAdapter(adapter);
    }

}
