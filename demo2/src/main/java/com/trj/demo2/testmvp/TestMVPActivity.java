package com.trj.demo2.testmvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.trj.demo2.R;
import com.trj.demo2.base.DMVPActivity;
import com.trj.demo2.bean.RespBean;
import com.trj.demo2.bean.Users;
import com.trj.demo2.databinding.ActivityTestMvpBinding;
import com.trj.demo2.http.DemoModel;
import com.trj.demo2.testmvp.recyclerview.TestRecyclerviewActivity;
import com.trj.tbase.module.titlemodule.TitleModule;
import com.trj.tlib.uils.ToastUtil;

/**
 * 测试MVP + DataBinding
 *
 */
public class TestMVPActivity extends DMVPActivity<TestView,TestPresenter> implements TestView {

    private ActivityTestMvpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_mvp);
        initView();
    }

    private TitleModule titleModule;

    @Override
    protected void initView() {
        titleModule = new TitleModule(context, rootView);
        // 设置title操作事件的监听
//        titleModule.setListenter(this);//根据需求添加
        titleModule.initTitle("测试MVP的activity");
        titleModule.initTitleMenu(TitleModule.MENU_TEXT,"添加");
        titleModule.setTitleElevation(2);
    }

    @Override
    protected TestPresenter initPersenter() {
        return new TestPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    public void onClickTextMvp(View view) {
        showDialog("登陆中...");
        getPresenter().login("15091288100", "123456");
    }
    public void onClickTextPost(View view) {
        getPresenter().test();
    }

    public void onClickTextList(View view) {
        skipActivity(TestRecyclerviewActivity.class);
    }

    private Users users;

    @Override
    public void loginSuccess(Users users) {
        this.users = users;
        ToastUtil.showToast(context,"登陆成功");
        //        关联数据
        binding.setUser(users);

        this.users.setMsg(users.toString());
    }

    @Override
    public void postSuccess(RespBean bean) {
        users.setMsg(new Gson().toJson(bean));
    }

    public void onClickTitleBgRed(View view) {
        titleModule.setTitleBackground(R.color.color_red);
    }

    public void onClickTitleBgBlue(View view) {
        titleModule.setTitleBackground(R.color.color_blue);
    }

    public void onClickTitleBgGreen(View view) {
        titleModule.setTitleBackground(R.color.color_green);
    }

}
