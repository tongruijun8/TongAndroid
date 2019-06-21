package com.trj.demo2.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.demo2.adapter.ImgAdapter;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tbase.tdialog.TLookImgsDialog2;
import com.trj.tlib.assist.ImgPaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LookBigImgActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_big_img);
    }

    private GridView gridView;
    private List<String> dataList;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("查看图片列表", true);
        dataList = Arrays.asList(ImgPaths.path);
        gridView = findViewById(R.id.gridview);
        ImgAdapter adapter = new ImgAdapter(context, dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<TLookImgsDialog2.ImageInfoBean> views = new ArrayList<>();
        TLookImgsDialog2.ImageInfoBean infoBean = new TLookImgsDialog2.ImageInfoBean();
        infoBean.setView((ImageView) view.findViewById(R.id.item_imageview));
        infoBean.setImgPath(dataList.get(position));
        views.add(infoBean);
        new TLookImgsDialog2.Builder(this)
                .setImgPath(views)
                .create()
                .show(getSupportFragmentManager(),"test");
    }
}
