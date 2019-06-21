package com.trj.tbase.tdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.trj.tbase.R;

/**
 * 加载弹框：默认居中显示
 */
public class TLoadingDialog extends BaseDialog {

    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    public static class Builder {

        protected int gravity;
        @FloatRange(from = 0.0, to = 1.0)
        protected float alpha;
        protected boolean cancelable = true;// 默认可以取消
        private String msg;
        private LayoutInflater inflater;

        public Builder(Context context) {
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.2f;
            gravity = Gravity.CENTER;
        }
        public Builder setMessage(String msgStr){
            msg = msgStr;
            return this;
        }
        public Builder setCancelable(boolean cancelable ){
            this.cancelable = cancelable;
            return this;
        }

        //ef：Gravity.CENTER
        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public TLoadingDialog create(){
            TLoadingDialog dialog = new TLoadingDialog();
            dialog.builder = this;
            dialog.contentView =inflater.inflate(R.layout.tdialog, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            if (msg != null && !msg.equals("")) {
                dialog.viewHolder.tdialogText.setText(msg);
            }
            return dialog;
        }
    }

    public void setMessage(String msg){
        viewHolder.tdialogText.setText(msg);
    }

    public boolean isShowing(){
        try {
            return getDialog().isShowing();
        }catch (Exception e){
            return false;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.MyDialog);
    }

    //一般用于创建复杂内容弹窗或全屏展示效果的场景，UI 复杂，功能复杂，一般有网络请求等异步操作。
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return contentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        //设置外部点击不取消
        if(!builder.cancelable){
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
        lp.dimAmount = builder.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        lp.windowAnimations = com.bigkoo.pickerview.R.style.picker_view_scale_anim;
        window.setAttributes(lp);
    }

    static class ViewHolder {

        View view;
        TextView tdialogText;

        ViewHolder(View view) {
            this.view = view;
            tdialogText = view.findViewById(R.id.tdilog_text);
        }

    }
    
}
