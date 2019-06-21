package com.trj.tbase.tdialog;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tbase.activity.BaseActivity;

/**
 * 此类已被遗弃，仅供参考使用
 * 推荐使用
 * @see BaseDialog
 */
@Deprecated
public class TPopupWindow implements View.OnClickListener {

    private Builder builder;

    private View contentView;
    private ViewHolder viewHolder;
    private PopupWindow popupWindow;

    public TPopupWindow(Builder builder) {
        this.builder = builder;
        contentView = LayoutInflater.from(builder.activity).inflate(R.layout.tdialog_comm, null);
        viewHolder = new ViewHolder(contentView);
    }

    public static class Builder {

        protected BaseActivity activity;

        protected String titleStr;
        protected String msgStr;
        protected int iconId;
        protected boolean cancelable = true;// 默认可以取消
        protected OnTDialogListener tDialogCancleListener;
        protected OnTDialogListener tDialogAffirmListener;
        protected String cancleText,affirmText;

        public Builder(BaseActivity activity) {
            this.activity = activity;
            cancelable = true;
        }
        public Builder setTitle(String titleStr){
            this.titleStr = titleStr;
            return this;
        }
        public Builder setMessage(String msgStr){
            this.msgStr = msgStr;
            return this;
        }
        public Builder setIcon(@DrawableRes int iconId){
            this.iconId = iconId;
            return this;
        }
        public Builder setCancelable(boolean cancelable ){
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancleClick(OnTDialogListener onTDialogListener) {
            return this.setOnCancleClick("",onTDialogListener);
        }
        public Builder setOnCancleClick(String cancleText,OnTDialogListener onTDialogListener) {
            this.cancleText = cancleText;
            this.tDialogCancleListener = onTDialogListener;
            return this;
        }
        public Builder setOnAffirmClick(OnTDialogListener onTDialogListener) {
            return this.setOnAffirmClick("",onTDialogListener);
        }
        public Builder setOnAffirmClick(String affirmText,OnTDialogListener onTDialogListener) {
            this.affirmText = affirmText;
            this.tDialogAffirmListener = onTDialogListener;
            return this;
        }


        public TPopupWindow create(){
            TPopupWindow dialog = new TPopupWindow(this);
            if (iconId > 0) {
                dialog.viewHolder.tdialogTop.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogIcon.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogIcon.setImageResource(iconId);
            }
            if (titleStr != null && !titleStr.equals("")) {
                dialog.viewHolder.tdialogTop.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogTitle.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogTitle.setText(titleStr);
            }
            if (msgStr != null && !msgStr.equals("")) {
                dialog.viewHolder.tdialogCenter.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogCenterMsg.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogCenterMsg.setText(msgStr);
            }
            if (cancleText == null || cancleText.equals("")) {
                dialog.viewHolder.tdialogBottomCancle.setText("取消");
            }else{
                dialog.viewHolder.tdialogBottomCancle.setText(cancleText);
            }
            if (affirmText == null || affirmText.equals("")) {
                dialog.viewHolder.tdialogBottomAffirm.setText("确认");
            }else{
                dialog.viewHolder.tdialogBottomAffirm.setText(cancleText);
            }
            if (tDialogCancleListener != null || tDialogAffirmListener != null) {
                dialog.viewHolder.tdialogBottom.setVisibility(View.VISIBLE);
            }
            return dialog;
        }
    }

    public void show() {
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setAnimationStyle(com.bigkoo.pickerview.R.style.picker_view_scale_anim);
        popupWindow.showAtLocation(builder.activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        builder.activity.rootMask.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.tdialog_bottom_cancle) {
            if(builder.tDialogCancleListener!=null){
                builder.tDialogCancleListener.onTdClick(viewHolder.tdialogBottomCancle);
            }
            builder.activity.rootMask.setVisibility(View.GONE);
            popupWindow.dismiss();
        }else if(ids == R.id.tdialog_bottom_affirm){
            if(builder.tDialogAffirmListener!=null){
                builder.tDialogAffirmListener.onTdClick(viewHolder.tdialogBottomAffirm);
            }
            builder.activity.rootMask.setVisibility(View.GONE);
            popupWindow.dismiss();
        }else if(ids == R.id.tdialog_all){
            if(builder.cancelable){
                builder.activity.rootMask.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        }else if(ids == R.id.tdialog_show){
        }
    }

    class ViewHolder {
        View view;
         RelativeLayout tdialogAll;
         LinearLayout tdialogShow;
         RelativeLayout tdialogTop;
         ImageView tdialogIcon;
         TextView tdialogTitle;
         RelativeLayout tdialogCenter;
         TextView tdialogCenterMsg;
         LinearLayout tdialogBottom;
         TextView tdialogBottomCancle;
         TextView tdialogBottomAffirm;

        ViewHolder(View view) {
            this.view = view;
            tdialogAll = view.findViewById(R.id.tdialog_all);
            tdialogShow = view.findViewById(R.id.tdialog_show);
            tdialogTop = view.findViewById(R.id.tdialog_top);
            tdialogIcon = view.findViewById(R.id.tdialog_icon);
            tdialogTitle = view.findViewById(R.id.tdialog_title);
            tdialogCenter = view.findViewById(R.id.tdialog_center);
            tdialogCenterMsg = view.findViewById(R.id.tdialog_center_msg);
            tdialogBottom = view.findViewById(R.id.tdialog_bottom);
            tdialogBottomCancle = view.findViewById(R.id.tdialog_bottom_cancle);
            tdialogBottomAffirm = view.findViewById(R.id.tdialog_bottom_affirm);
            tdialogAll.setOnClickListener(TPopupWindow.this);
            tdialogShow.setOnClickListener(TPopupWindow.this);
            tdialogBottomCancle.setOnClickListener(TPopupWindow.this);
            tdialogBottomAffirm.setOnClickListener(TPopupWindow.this);
        }
    }

}
