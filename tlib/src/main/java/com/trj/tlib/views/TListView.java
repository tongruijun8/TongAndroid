package com.trj.tlib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author tong
 * @date 2018/3/7 15:36
 */

public class TListView extends ListView {
    public TListView(Context context) {
        super(context);
    }

    public TListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TListView(Context context, AttributeSet attrs, int defStyleAttr) {
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
