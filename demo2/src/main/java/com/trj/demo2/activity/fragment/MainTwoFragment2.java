package com.trj.demo2.activity.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.trj.demo2.BR;
import com.trj.demo2.R;
import com.trj.demo2.base.DMVPFragment;
import com.trj.demo2.bean.PageBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.http.DemoModel;
import com.trj.demo2.testmvp.recyclerview.TestBaseViewHolder;
import com.trj.demo2.testmvp.recyclerview.TestRecyclerAdapter;
import com.trj.tbase.tdialog.BaseDialog;
import com.trj.tbase.tdialog.TCommonDialog;
import com.trj.tlib.bean.ContactsInfo;
import com.trj.tlib.uils.IntentUtil;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.PermissionRxUtil;
import com.trj.tlib.uils.PhoneUtil;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class MainTwoFragment2 extends DMVPFragment<TwoFmView,TwoFmPresenter> implements TwoFmView {

    private RecyclerView recyclerView;


    @Override
    protected TwoFmPresenter initPersenter() {
        return new TwoFmPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_two2, container, false);
        //添加内容View
        initFragmentView(rootView);
        return rootView;
    }


    protected void initFragmentView(View view) {
        recyclerView = view.findViewById(R.id.two_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity.context));

        permissionRxUtil = new PermissionRxUtil(this);
    }

    private PermissionRxUtil permissionRxUtil;
    private List<ContactsInfo> contactsInfoList = new ArrayList<>();

    @Override
    public void initData() {
        super.initData();
        if (contactsInfoList == null || contactsInfoList.size() == 0) {
            String[] miss = {Manifest.permission.READ_CONTACTS};
            permissionRxUtil.apply(new PermissionRxUtil.ApplyEvent() {
                @Override
                public void accept(Boolean b) {
                    if (b) {
                        getContacts();
                    } else {
                        ToastUtil.showToast(activity.context, "请开启读取权限");
                    }
                }
            }, miss);
        }
    }

    private void getContacts() {
        PhoneUtil phoneUtil = new PhoneUtil(activity.context);
        contactsInfoList.addAll(phoneUtil.getPhone());
        Logger.t("---" + new Gson().toJson(contactsInfoList));
        initAdapter();
        getPresenter().addUserAll(contactsInfoList);
    }


    private void initAdapter() {

        final int variableId = BR.contactsInfo;

        TestRecyclerAdapter adapter = new TestRecyclerAdapter<ContactsInfo>(R.layout.item_recycler2, contactsInfoList) {
            @Override
            protected void convert(TestBaseViewHolder helper, ContactsInfo item) {
                helper.setBinding(variableId, item);
            }
        };
        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final ContactsInfo contactsInfo = (ContactsInfo) adapter.getData().get(position);
                ToastUtil.showToast(activity.context, "点击了" + contactsInfo.getName());
                String[] miss = {Manifest.permission.CALL_PHONE};
                permissionRxUtil.apply(new PermissionRxUtil.ApplyEvent() {
                    @Override
                    public void accept(Boolean b) {
                        if (b) {
                            callPhone(contactsInfo);
                        } else {
                            ToastUtil.showToast(activity.context, "请开启读取权限");
                        }
                    }
                }, miss);

            }
        });

        adapter.openLoadAnimation();
//        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);

        recyclerView.setAdapter(adapter);
    }

    private void callPhone(final ContactsInfo contactsInfo) {

        new TCommonDialog.Builder(activity.context)
                .setMessage("确认要拨打电话给： " + contactsInfo.getName() + " ?")
                .setMessageGravity(Gravity.CENTER)
                .setOnAffirmClick("拨打", new BaseDialog.OnTdfListener() {
                    @Override
                    public boolean onTdClick(View view, String msg) {
                        new IntentUtil().callPhoneNumber(activity.context,contactsInfo.getTelPhone().replace(" ", ""));
                        return true;
                    }
                })
                .create()
                .show(getFragmentManager(),"tdialog_callphone");

    }

    @Override
    public void onDestroy() {
        permissionRxUtil.onDestroy();
        super.onDestroy();
    }


    @Override
    public void bindListData(PageBean<Users> pageBean) {

    }
}
