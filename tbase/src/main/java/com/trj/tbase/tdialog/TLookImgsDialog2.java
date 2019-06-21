package com.trj.tbase.tdialog;

import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.trj.tbase.R;
import com.trj.tbase.adapter.ImagePagerAdapter;
import com.trj.tlib.uils.GlideUtile;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的 Dialog 布局
 */
public class TLookImgsDialog2 extends BaseDialog {


    private View contentView;
    private Builder builder;

    private static final int DURATION = 200;
    private int mLeftDelta;
    private int mTopDelta;
    private float scale;

    public static class Builder {
        private FragmentActivity fragmentActivity;

        protected ViewHolder viewHolder;

        protected List<ImageInfoBean> views;
        protected List<View> viewList = new ArrayList<>();
        //    第几张图片
        private int index = 0;

        protected ImagePagerAdapter adapter;
//        protected OnTdLookImgClickListener listener;

        private LayoutInflater inflater;

        public Builder(FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
            inflater = LayoutInflater.from(fragmentActivity);
        }

        public Builder setImgPath(List<ImageInfoBean> views) {
            this.views = views;
            if (views != null && views.size() > 0) {
                int len = views.size();
                for (int i = 0; i < len; i++) {
                    View view = inflater.inflate(R.layout.item_image, null);
                    ImageView imageView = view.findViewById(R.id.item_image_imageview);
                    GlideUtile.bindImageView(fragmentActivity.getBaseContext(), views.get(i).getImgPath(), imageView);
                    viewList.add(view);
                }
            }
            return this;
        }


        public Builder setPosition(int position) {
            index = position;
            return this;
        }

        public TLookImgsDialog2 create() {
            TLookImgsDialog2 dialog = new TLookImgsDialog2();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.td_look_imgs, null);
            viewHolder = new ViewHolder(dialog.contentView);
            adapter = new ImagePagerAdapter(viewList);
            viewHolder.tdLookViewPager.setAdapter(adapter);
            viewHolder.tdLookViewPager.setCurrentItem(index, false);
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

        builder.viewHolder.tdLookViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                builder.index = position;
                initPositionData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initClickEvent();

        initPositionData();

        enterAnimation();

        return contentView;
    }

    private void initClickEvent() {
        if (builder.viewList != null && builder.viewList.size() > 0) {
            int len = builder.viewList.size();
            for (int i = 0; i <len; i++) {
                final View view = builder.viewList.get(i);
                view.setOnClickListener(new View.OnClickListener() {
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
            }
        }
    }

    private void initPositionData() {

        ImageView view1 = builder.views.get(builder.index).getView();
        int[] location = new int[2];
//        view1.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含statusBar
        view1.getLocationInWindow(location);

        mLeftDelta = location[0];
        mTopDelta = location[1] ;
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        float mWidthScale = view1.getWidth() * 1.0f / width;
        float  mHeightScale = view1.getHeight() * 1.0f / height;
        scale = Math.max(mWidthScale, mHeightScale);
        if (height >= width) {// 竖屏
            mTopDelta = (int) (mTopDelta - (height - width) * scale / 2);
        } else {//横屏
            mLeftDelta = (int) (mLeftDelta - (width - height) * scale / 2);
        }

//        Logger.t("---x = "+ location[0]);
//        Logger.t("---y = "+ location[1]);
//        Logger.t("---ww = "+ view1.getWidth());
//        Logger.t("---hh = "+ view1.getHeight());
//        Logger.t("---width = "+ getResources().getDisplayMetrics().widthPixels);
//        Logger.t("---height = "+ getResources().getDisplayMetrics().heightPixels);
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        hideStausbar(true);
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        hideStausbar(false);
        super.dismiss();
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
        ViewPager tdLookViewPager;

        ViewHolder(View view) {
            this.view = view;
            tdLookViewPager = view.findViewById(R.id.td_look_viewpager);
        }
    }


    // 进入动画
    public void enterAnimation() {
        builder.viewHolder.view.setBackgroundColor(Color.parseColor("#000000"));
        // 设置imageview动画的初始值
        builder.viewHolder.tdLookViewPager.setPivotX(0);
        builder.viewHolder.tdLookViewPager.setPivotY(0);
        builder.viewHolder.tdLookViewPager.setScaleX(scale);
        builder.viewHolder.tdLookViewPager.setScaleY(scale);
        builder.viewHolder.tdLookViewPager.setTranslationX(mLeftDelta);
        builder.viewHolder.tdLookViewPager.setTranslationY(mTopDelta);
// 设置动画
        TimeInterpolator sDecelerator = new DecelerateInterpolator();
// 设置imageview缩放动画,以及缩放开始位置
        builder.viewHolder.tdLookViewPager.animate().setDuration(DURATION).scaleX(1).scaleY(1).translationX(0).translationY(0).setInterpolator(sDecelerator);
// 设置activity主布局背景颜色DURATION毫秒内透明度从透明到不透明
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
//        bgAnim.setDuration(DURATION);
//        bgAnim.start();
    }

    public void exitAnimation(final Runnable endAction) {
        builder.viewHolder.view.setBackgroundColor(Color.parseColor("#00000000"));
        TimeInterpolator sInterpolator = new AccelerateInterpolator();
//         设置imageview缩放动画,以及缩放结束位置
        builder.viewHolder.tdLookViewPager.animate().setDuration(DURATION).scaleX(scale).scaleY(scale).
                translationX(mLeftDelta).translationY(mTopDelta).setInterpolator(sInterpolator).withEndAction(endAction);

//         设置activity主布局背景颜色DURATION毫秒内透明度从不透明到透明
//        ColorDrawable colorDrawable = (ColorDrawable) builder.viewHolder.view.getBackground();
//        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
//        bgAnim.setDuration(DURATION);
//        bgAnim.start();
    }

    //true隐藏，false显示
    private void hideStausbar(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams attrs = builder.fragmentActivity.getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            builder.fragmentActivity.getWindow().setAttributes(attrs);
        } else {
            WindowManager.LayoutParams attrs = builder.fragmentActivity.getWindow().getAttributes();
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            builder.fragmentActivity.getWindow().setAttributes(attrs);
        }
    }


    //控件对象的参数
    public static class ImageInfoBean {

        private ImageView view;

        private Object imgPath;

        public ImageView getView() {
            return view;
        }

        public void setView(ImageView view) {
            this.view = view;
        }

        public Object getImgPath() {
            return imgPath;
        }

        public void setImgPath(Object imgPath) {
            this.imgPath = imgPath;
        }

        @Override
        public String toString() {
            return "ImageInfoBean{" +
                    "imgPath=" + imgPath +
                    '}';
        }
    }


}
