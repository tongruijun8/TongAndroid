package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

@Deprecated
public class TTextView extends TBaseView {

    private TextPaint paintText;

    //    内容
    private String textContent = "";
    //    文本色
    private int colorText;
    //    内容
    private int textContentSize = 24;

    public TTextView(Context context) {
        super(context);
    }

    public TTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute();
    }

    @Override
    public void initAttribute() {
        textContent = "skjdhfg宣讲会根据第三方";
        textContentSize = 24;
        colorText = Color.parseColor("#212121");

        paintText =new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(colorText);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStrokeCap(Paint.Cap.SQUARE);
        paintText.setTextSize(textContentSize);
    }

    @Override
    public void initXmlAttribute(AttributeSet attrs) {

    }


    @Override
    public void initPaint() {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(textContent==null&&textContent.equals("")){
            return;
        }
        StaticLayout staticLayout = new StaticLayout(textContent, paintText, width - 20 - 1, Layout.Alignment.ALIGN_OPPOSITE, 1.0f, 0.0f, false);
        staticLayout.draw(canvas);
    }


}
