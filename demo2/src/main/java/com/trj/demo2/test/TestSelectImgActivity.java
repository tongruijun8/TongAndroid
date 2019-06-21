package com.trj.demo2.test;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tbase.module.select_img_module.SelectImageListener;
import com.trj.tbase.module.select_img_module.SelectImageModule;
import com.trj.tlib.uils.GlideUtile;
import com.trj.tlib.uils.IntentUtil;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.PermissionRxUtil;
import com.trj.tlib.uils.ToastUtil;

import java.util.List;

public class TestSelectImgActivity extends BaseActivity {

    private ImageView imageview;

    private SelectImageModule selectImageModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_select_img);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试选择图片");

        imageview = findViewById(R.id.imageview);

        selectImageModule = new SelectImageModule(this);
    }

    private PermissionRxUtil permissionRxUtil;

    public void selectImg(View view) {
        //相机的权限
//        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        String[] perms = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //打电话的权限
//        String[] perms = {Manifest.permission.CALL_PHONE};
        permissionRxUtil = new PermissionRxUtil(this);
        permissionRxUtil.apply(b -> {
            if(b){
                //授权
                selectImageModule.selectImage(3, photos -> {
//                            ToastUtil.showToast(context, "path1 = " + photos.get(0));
                    Logger.t("-----path = " + photos.get(0));
                    GlideUtile.bindImageView(context,photos.get(0),imageview);
                });
//                    ToastUtil.showToast(context,"授权该权限");
            }else{
                //未授权
                ToastUtil.showToast(context,"您没有授权该权限，请在设置中打开权限");
            }
        },perms);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectImageModule.onActivityResult(requestCode, resultCode, data);
    }

    private PermissionRxUtil permissionRxUtil2;
    public void callPhone(final String phoneNum){
        //打电话的权限
        String[] perms = {Manifest.permission.CALL_PHONE};
        permissionRxUtil2 = new PermissionRxUtil(this);
        permissionRxUtil2.apply(b -> {
            if(b){
                //授权
                IntentUtil intentUtil = new IntentUtil();
                intentUtil.callPhoneNumber(context,phoneNum);

//                    ToastUtil.showToast(context,"授权该权限");
            }else{
                //未授权
                ToastUtil.showToast(context,"您没有授权该权限，请在设置中打开权限");
            }
        },perms);
    }

    @Override
    protected void onDestroy() {
        if (permissionRxUtil != null) {
            permissionRxUtil.onDestroy();
        }
        if (permissionRxUtil2 != null) {
            permissionRxUtil2.onDestroy();
        }
        super.onDestroy();
    }

    public void callPhoneNumber(View view) {
        callPhone("15091288100");
    }

    @Override
    public void setEndActivityAnim(int enterAnim, int exitAnim) {
        super.setEndActivityAnim(R.anim.activity_finish_in_app, R.anim.activity_finish_out_app);
    }
}
