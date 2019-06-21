package com.trj.tlib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @author tong
 * @date 2018/3/7 15:36
 */

public class TWebView extends WebView {
    public TWebView(Context context) {
        super(context);
    }

    public TWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
