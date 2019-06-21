package com.trj.demo2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;

import com.trj.demo2.bean.Users;
import com.trj.demo2.databinding.ActivityTestDatabindingBinding;
import com.trj.tbase.module.titlemodule.TitleListenter;
import com.trj.tbase.module.titlemodule.TitleModule;
import com.trj.tlib.uils.ToastUtil;
import com.trj.tlib.uils.ToastUtil2;

import java.util.Random;

public class TestDataBindingActivity extends AppCompatActivity implements TitleListenter {

    private ActivityTestDatabindingBinding binding;

    private Users u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_databinding);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_databinding);

        View view = findViewById(android.R.id.content);

        TitleModule titleModule = new TitleModule(this, view);
        titleModule.setListenter(this);
        titleModule.initTitle("Databinding 测试", true);

        //关联方法
        binding.setPresenter(new Presenter());
//        关联数据
        u = new Users();
        u.setUser_name("zhangsan");
        u.setUser_address("陕西省西安市碑林区高新三路2号海佳云顶1号楼1703室");
        binding.setUser(u);

        binding.testName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.setUser_name("点击改变后的文字" + new Random().nextInt(10));
            }
        });

    }

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
//
//    @Override
//    protected void initView() {
//        super.initView();
//        titleModule.initTitle("测试DataBinding", true);
//
//    }


    public class Presenter {

        public void onClickAddress(View view) {
            ToastUtil2.showToast(TestDataBindingActivity.this,"进来了");
            u.setUser_address("点击改变后的地址：" + new Random().nextInt(10));

        }

        public void afterTextChanged(Editable s) {
            ToastUtil.showToast(TestDataBindingActivity.this, s.toString());

        }
    }

}
