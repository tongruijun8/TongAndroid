package com.trj.tbase.dialog;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;

public class KcPopupWindow implements View.OnClickListener {

    private AppCompatActivity activity;

    private String string1 = "";
    private String string2 = "";
    private String string3 = "";
    private String string4 = "";

    private View view;

    public KcPopupWindow(AppCompatActivity activity, View view) {
        this.activity = activity;
        this.view = view;
        contentView = LayoutInflater.from(activity).inflate(R.layout.item_kecheng, null);
        viewHolder = new ViewHolder(contentView);
        viewHolder.itemKechengBgRl.setOnClickListener(this);
        viewHolder.itemKechengShowRl.setOnClickListener(this);
    }

//    public KcPopupWindow(Activity activity, String string1, String string2, String string3, String string4) {
//        this.activity = activity;
//        setContent(string1, string2, string3, string4);
//    }

    public void setContent(String string1, String string2, String string3, String string4){
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
        this.string4 = string4;
        initText();
    }

    private void initText() {
        viewHolder.itemKechengName.setText(string1);
        viewHolder.itemKechengAddress.setText(string2);
        viewHolder.itemKechengTea.setText(string3);
        viewHolder.itemKechengGrade.setText(string4);
    }

    private View contentView;
    private ViewHolder viewHolder;
    private PopupWindow popupWindow;

    public void show() {
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setAnimationStyle(com.bigkoo.pickerview.R.style.picker_view_scale_anim);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.item_kecheng_bg_rl) {
            view.setVisibility(View.GONE);
            popupWindow.dismiss();
        }else if(ids == R.id.item_kecheng_show_rl){

        }
    }

    class ViewHolder {
        View view;
         RelativeLayout itemKechengBgRl;
         RelativeLayout itemKechengShowRl;
         TextView itemKechengName;
         TextView itemKechengAddress;
         TextView itemKechengTea;
         TextView itemKechengGrade;

        ViewHolder(View view) {
            this.view = view;
            itemKechengBgRl = view.findViewById(R.id.item_kecheng_bg_rl);
            itemKechengShowRl = view.findViewById(R.id.item_kecheng_show_rl);
            itemKechengName = view.findViewById(R.id.item_kecheng_name);
            itemKechengAddress = view.findViewById(R.id.item_kecheng_address);
            itemKechengTea = view.findViewById(R.id.item_kecheng_tea);
            itemKechengGrade = view.findViewById(R.id.item_kecheng_grade);
        }
    }

}
