package com.trj.tlib.views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author tong
 * @date 2018/7/16 17:15
 */
public class THScrollView extends TScrollView {

    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public THScrollView(Context context) {
        super(context);
    }

    private int scrollHH = 0;

    public THScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        scrollHH = getResources().getDimensionPixelOffset(R.dimen.jfdh_h);
    }

    public THScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollHH(int scrollHH) {
        this.scrollHH = scrollHH;
    }

    public interface OnScrollListener{
        void onScrollGt();
        void onScrollLt();
    }

    private boolean isDa = false;
    private boolean isXiao = false;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t >= scrollHH) {
            if(!isDa){
                isDa = true;
                isXiao = false;
                if(listener != null){
                    listener.onScrollGt();
                }
            }
        } else if (t < scrollHH) {
            if(!isXiao){
                isDa = false;
                isXiao = true;
                if(listener != null){
                    listener.onScrollLt();
                }
            }
        }
    }
}
