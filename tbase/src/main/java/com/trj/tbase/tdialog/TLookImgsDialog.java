package com.trj.tbase.tdialog;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.trj.tbase.R;

/**
 * 自定义的 Dialog 布局
 */
public class TLookImgsDialog extends BaseDialog implements View.OnClickListener {

    private ViewHolder viewHolder;
    private View contentView;
    private Builder builder;

    private static final int DURATION = 300;
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private ColorDrawable colorDrawable;


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.td_look_imageview) {
            if (builder.listener != null) {
                builder.listener.onTdImgClick();
            }
            dismiss();
        }
    }

    public static class Builder {
        private Context context;

//        protected List<String> stringList;

        protected Object pathImg;
        protected int top, left, width, height;
        protected OnTdLookImgClickListener listener;

        private LayoutInflater inflater;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        public Builder setImgPath(Object imgPath) {
            this.pathImg = imgPath;
            return this;
        }

        public Builder setImgPositionAndSize(int top, int left, int width, int height) {
            this.top = top;
            this.left = left;
            this.width = width;
            this.height = height;
            return this;
        }

//        public Builder setList(List<String> stringList) {
//            if (stringList != null && stringList.size() > 0) {
//                this.stringList.addAll(stringList);
//            }
//            return this;
//        }

        public Builder setOnTdLookImgClick(OnTdLookImgClickListener onTdLookImgClickListener) {
            this.listener = onTdLookImgClickListener;
            return this;
        }

        public TLookImgsDialog create() {
            TLookImgsDialog dialog = new TLookImgsDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.td_look_img, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);
            Glide.with(context).load("http://b-ssl.duitang.com/uploads/item/201607/25/20160725185442_K2ScQ.jpeg").into(dialog.viewHolder.tdLookImageView);
            return dialog;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialogFullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHolder.tdLookImageView.setOnClickListener(this);

        int screenww = getResources().getDisplayMetrics().widthPixels;
        int screenhh = getResources().getDisplayMetrics().heightPixels;

        mLeftDelta = builder.left;
        mTopDelta = builder.top;
        mWidthScale = (float) builder.width / screenww;
        mHeightScale = (float) builder.height / screenhh;
        colorDrawable = new ColorDrawable(Color.BLACK);
        viewHolder.tdLookImageView.setBackground(colorDrawable);

        enterAnimation();

        viewHolder.tdLookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitAnimation(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                });
            }
        });
        return contentView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onStart() {
        super.onStart();
        //经测试，在这里设置背景色才起作用
        Window window = getDialog().getWindow();
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        //让宽度充满屏幕
        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = 0.4f;
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        lp.windowAnimations = R.style.DialogBottomAnimation;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        ImageView tdLookImageView;

        ViewHolder(View view) {
            this.view = view;
            tdLookImageView = view.findViewById(R.id.td_look_imageview);

        }
    }


    // 进入动画
    public void enterAnimation() {
        // 设置imageview动画的初始值
        viewHolder.tdLookImageView.setPivotX(0);
        viewHolder.tdLookImageView.setPivotY(0);
        viewHolder.tdLookImageView.setScaleX(mWidthScale);
        viewHolder.tdLookImageView.setScaleY(mHeightScale);
        viewHolder.tdLookImageView.setTranslationX(mLeftDelta);
        viewHolder.tdLookImageView.setTranslationY(mTopDelta);
// 设置动画
        TimeInterpolator sDecelerator = new DecelerateInterpolator();
// 设置imageview缩放动画,以及缩放开始位置
        float scale = Math.min(mWidthScale, mHeightScale);
        viewHolder.tdLookImageView.animate().setDuration(DURATION).scaleX(1/mWidthScale).scaleY(1/mHeightScale).scaleXBy(1).scaleYBy(1).translationX(0).translationY(0).setInterpolator(sDecelerator);
// 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 150, 255);
        bgAnim.setDuration(DURATION);
        bgAnim.start();
    }

    public void exitAnimation(final Runnable endAction) {
        viewHolder.tdLookImageView.setBackground(new ColorDrawable(Color.TRANSPARENT));
        TimeInterpolator sInterpolator = new AccelerateInterpolator();
//         设置imageview缩放动画,以及缩放结束位置
        viewHolder.tdLookImageView.animate().setDuration(DURATION).scaleX(mWidthScale).scaleY(mHeightScale).scaleXBy(1).scaleYBy(1).
                translationX(mLeftDelta).translationY(mTopDelta).setInterpolator(sInterpolator).withEndAction(endAction);
//         设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 150,0);
//        bgAnim.setDuration(DURATION);
//        bgAnim.start();
    }

}
