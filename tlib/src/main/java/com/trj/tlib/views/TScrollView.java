package com.trj.tlib.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author tong
 * @date 2018/6/30 11:59
 */
public class TScrollView extends ScrollView {
    public TScrollView(Context context) {
        super(context);
    }

    public TScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 解决scrollview自动滚动到底部的问题
     * @param rect
     * @return
     */
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

}
