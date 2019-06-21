package com.trj.tbase.tdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tlib.uils.Logger;


/**
 * 一般的弹框
 * <p>
 * 包括：标题、内容布局 和 底部按钮
 */
public class TCommonDialog extends BaseDialog implements View.OnClickListener {

    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        String msg = "";
        if (builder.editable) msg = viewHolder.tdialogCenterMsgEdit.getText().toString().trim();
        else msg = viewHolder.tdialogCenterMsgText.getText().toString().trim();
        if (ids == R.id.tdialog_bottom_cancle) {
            if (builder.tDialogCancleListener != null) {
                boolean isHide = builder.tDialogCancleListener.onTdClick(viewHolder.tdialogBottomCancle, msg);
                if (isHide) {
                    dismiss();
                } else {
                    return;
                }
            } else {
                dismiss();
            }
        } else if (ids == R.id.tdialog_bottom_affirm) {
            if (builder.tDialogAffirmListener != null) {
                boolean isHide = builder.tDialogAffirmListener.onTdClick(viewHolder.tdialogBottomAffirm, msg);
                if (isHide) {
                    dismiss();
                } else {
                    return;
                }
            } else {
                dismiss();
            }
        }
    }

    public static class Builder {
        private Context context;
        protected String titleStr;
        protected boolean editable;
        protected String msgStr;
        protected String msgHintStr;
        protected int msgLength;
        protected int msgGravity;
        protected int horizontalMargin;
        protected int gravity;
        @FloatRange(from = 0.0, to = 1.0)
        protected float alpha;
        protected boolean cancelable;// 默认可以取消
        protected int iconId;
        protected View view;
        protected int layoutId;
        protected OnTdfListener tDialogCancleListener;
        protected OnTdfListener tDialogAffirmListener;
        protected String cancleText, affirmText;

        private LayoutInflater inflater;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.4f;
            gravity = Gravity.CENTER;
            horizontalMargin = context.getResources().getDimensionPixelOffset(R.dimen.dialog_m_h);
            editable = false;
            msgHintStr = "";
            msgLength = -1;
            Logger.t("---------------mar = " + horizontalMargin);
        }

        public Builder setTitle(String titleStr) {
            this.titleStr = titleStr;
            return this;
        }

        public Builder setMessage(String msgStr) {
            this.msgStr = msgStr;
            return this;
        }

        public Builder setMessageHint(String msgHintStr) {
            this.msgHintStr = msgHintStr;
            return this;
        }

        public Builder setMessageLength(int msgLength) {
            this.msgLength = msgLength;
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            this.msgGravity = gravity;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            this.iconId = iconId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setView(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        //ef：Gravity.CENTER
        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setHorizontalMargin(int horizontalMargin) {
            final float scale = context.getResources().getDisplayMetrics().density;
            this.horizontalMargin = (int) (horizontalMargin * scale + 0.5f);
            return this;
        }

        public Builder setOnCancleClick(OnTdfListener onTDialogListener) {
            return this.setOnCancleClick("", onTDialogListener);
        }

        public Builder setOnCancleClick(String cancleText, OnTdfListener onTDialogListener) {
            this.cancleText = cancleText;
            this.tDialogCancleListener = onTDialogListener;
            return this;
        }

        public Builder setOnAffirmClick(OnTdfListener onTDialogListener) {
            return this.setOnAffirmClick("", onTDialogListener);
        }

        public Builder setOnAffirmClick(String affirmText, OnTdfListener onTDialogListener) {
            this.affirmText = affirmText;
            this.tDialogAffirmListener = onTDialogListener;
            return this;
        }


        public TCommonDialog create() {
            TCommonDialog dialog = new TCommonDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.tdialog_comm_test, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);
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
            if (!TextUtils.isEmpty(msgStr) || !TextUtils.isEmpty(msgHintStr)) {
                dialog.viewHolder.tdialogCenter.setVisibility(View.VISIBLE);
                if (editable) {
                    dialog.viewHolder.tdialogCenterMsgEdit.setVisibility(View.VISIBLE);
                    if(!TextUtils.isEmpty(msgStr)) dialog.viewHolder.tdialogCenterMsgEdit.setText(msgStr);
                    if(!TextUtils.isEmpty(msgHintStr)) dialog.viewHolder.tdialogCenterMsgEdit.setHint(msgHintStr);
                    if (msgGravity > 0) dialog.viewHolder.tdialogCenterMsgEdit.setGravity(msgGravity);
                    //长度过滤
                    if (msgLength > 0) dialog.viewHolder.tdialogCenterMsgEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(msgLength)});
                } else {
                    dialog.viewHolder.tdialogCenterMsgText.setVisibility(View.VISIBLE);
                    if(!TextUtils.isEmpty(msgStr)) dialog.viewHolder.tdialogCenterMsgText.setText(msgStr);
                    if(!TextUtils.isEmpty(msgHintStr)) dialog.viewHolder.tdialogCenterMsgText.setHint(msgHintStr);
                    if (msgGravity > 0) dialog.viewHolder.tdialogCenterMsgText.setGravity(msgGravity);
                }
            }
            if (view != null) {
                dialog.viewHolder.tdialogCenter.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogCenter.removeAllViews();
                dialog.viewHolder.tdialogCenter.addView(view);
            }
            if (layoutId > 0) {
                dialog.viewHolder.tdialogCenter.setVisibility(View.VISIBLE);
                dialog.viewHolder.tdialogCenter.removeAllViews();
                View view = inflater.inflate(layoutId, null);
                dialog.viewHolder.tdialogCenter.addView(view);
            }
            if (cancleText == null || cancleText.equals("")) {
                dialog.viewHolder.tdialogBottomCancle.setText("取消");
            } else {
                dialog.viewHolder.tdialogBottomCancle.setText(cancleText);
            }
            if (affirmText == null || affirmText.equals("")) {
                dialog.viewHolder.tdialogBottomAffirm.setText("确认");
            } else {
                dialog.viewHolder.tdialogBottomAffirm.setText(affirmText);
            }
            if (tDialogCancleListener != null || tDialogAffirmListener != null) {
                dialog.viewHolder.tdialogBottom.setVisibility(View.VISIBLE);
            }
            return dialog;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    //一般用于创建复杂内容弹窗或全屏展示效果的场景，UI 复杂，功能复杂，一般有网络请求等异步操作。
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHolder.tdialogBottomCancle.setOnClickListener(this);
        viewHolder.tdialogBottomAffirm.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        if (builder.editable && TextUtils.isEmpty(builder.msgStr)) {
            viewHolder.tdialogCenterMsgEdit.setSelection(builder.msgStr.length());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置外部点击不取消
        if (!builder.cancelable) {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
        //经测试，在这里设置背景色才起作用
        Window window = getDialog().getWindow();
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setBackgroundDrawableResource(R.color.colorAccent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = builder.gravity;
        //让宽度充满屏幕
//        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.width = getResources().getDisplayMetrics().widthPixels - builder.horizontalMargin * 2;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = builder.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = com.bigkoo.pickerview.R.style.picker_view_scale_anim;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        RelativeLayout tdialogTop;
        ImageView tdialogIcon;
        TextView tdialogTitle;
        RelativeLayout tdialogCenter;
        TextView tdialogCenterMsgText;
        EditText tdialogCenterMsgEdit;
        LinearLayout tdialogBottom;
        TextView tdialogBottomCancle;
        TextView tdialogBottomAffirm;

        ViewHolder(View view) {
            this.view = view;
            tdialogTop = view.findViewById(R.id.tdialog_top);
            tdialogIcon = view.findViewById(R.id.tdialog_icon);
            tdialogTitle = view.findViewById(R.id.tdialog_title);
            tdialogCenter = view.findViewById(R.id.tdialog_center);
            tdialogCenterMsgText = view.findViewById(R.id.tdialog_center_msg_text);
            tdialogCenterMsgEdit = view.findViewById(R.id.tdialog_center_msg_edit);
            tdialogBottom = view.findViewById(R.id.tdialog_bottom);
            tdialogBottomCancle = view.findViewById(R.id.tdialog_bottom_cancle);
            tdialogBottomAffirm = view.findViewById(R.id.tdialog_bottom_affirm);
        }
    }

}
