package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;

import com.trj.tlib.uils.Logger;

public abstract class TBaseView extends View {

    protected Context context;
    protected int width = 0;
    protected int height = 0;

    public TBaseView(Context context) {
        super(context);
        this.context = context;
        initAttribute();
        initPaint();
    }

    public TBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initXmlAttribute(attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Logger.t("ww = " + width);
        Logger.t("hh = " + height);
    }

    /**
     * 初始化属性值
     */
    public abstract void initAttribute();

    /**
     * 初始化Xml的属性值
     */
    public abstract void initXmlAttribute(AttributeSet attrs);

    /**
     * 初始化Xml的属性值
     */
    public abstract void initPaint();


    public TypedArray getTypedArray(AttributeSet attrs,@StyleableRes int[] coustomAttrs){
        return context.obtainStyledAttributes(attrs, coustomAttrs);
    }

    /**
     * 刷新View
     */
    public void refreshView() {
        invalidate();
    }
}
