package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.trj.tlib.R;

public class RoundProgressbar extends View {

    private int width = 0;
    private int height = 0;

    //画笔
    private Paint paint;
    private TextPaint paintText;
    private Paint paintProgess;

    //圆环的进度计数
    @FloatRange(from = 0.0, to = 1.0)
    private float progress = 0;
    //    画笔的宽度
    private int paintWidth = 20;
    //    画笔文字的宽度
    private int paintTextWidth = 2;
    //  背景色
    private int colorBg;
    //    前置色
    private int colorProgress;
    //    文本色
    private int colorText;
    //    内容
    private String textContent = "";
    //    内容
    private int textContentSize = 24;


//    private int color[] = new int[3];   //渐变颜色

    public RoundProgressbar(Context context) {
        super(context);
        initAttribute();
    }

    public RoundProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressbar);
        paintWidth = a.getInteger(R.styleable.RoundProgressbar_ring_width, 20);
        textContentSize = a.getInteger(R.styleable.RoundProgressbar_ring_text_size, 24);
        progress = a.getFloat(R.styleable.RoundProgressbar_progress, 0.0f);
        colorBg = a.getColor(R.styleable.RoundProgressbar_color_bg, Color.parseColor("#333333"));
        colorProgress = a.getColor(R.styleable.RoundProgressbar_color_progress, Color.parseColor("#aa3399"));
        colorText = a.getColor(R.styleable.RoundProgressbar_color_text, Color.parseColor("#212121"));
        textContent = a.getString(R.styleable.RoundProgressbar_ring_text);
        a.recycle();
        paintTextWidth = 2;
    }

    private void initAttribute() {
        paintTextWidth = 2;
        paintWidth = 20;
        progress = 0.0f;
        textContentSize = 24;
        colorBg = Color.parseColor("#333333");
        colorProgress = Color.parseColor("#aa3399");
        colorText = Color.parseColor("#212121");
        textContent = "";
        //圆环渐变的颜色
//        color[0] = Color.parseColor("#FFD300");
//        color[1] = Color.parseColor("#FF0084");
//        color[2] = Color.parseColor("#16FF00");
    }

    public void setProgress(@FloatRange(from = 0.0f, to = 1.0) float progress) {
        this.progress = progress;
    }

    public void setColorBg(@ColorInt int colorBg) {
        this.colorBg = colorBg;
    }

    public void setColorProgress(@ColorInt int colorProgress) {
        this.colorProgress = colorProgress;
    }

    public void setPaintWidth(int paintWidth) {
        this.paintWidth = paintWidth;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setColorText(@ColorInt int colorText) {
        this.colorText = colorText;
    }

    public void setTextContentSize(int textContentSize) {
        this.textContentSize = textContentSize;
    }

    /**
     * 刷新控件
     */
    public void refresh(){
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(colorBg);
//        paint.setARGB(255, 100, 100, 100);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(paintWidth);

        paintProgess = new Paint();
        paintProgess.setColor(colorProgress);
        paintProgess.setStyle(Paint.Style.STROKE);
        paintProgess.setStrokeCap(Paint.Cap.ROUND);
        paintProgess.setStrokeWidth(paintWidth);
//        paintProgess.setShader(new SweepGradient(width / 2, height / 2, color, null));

//        paintText = new Paint();
//        paintText.setColor(colorText);
//        paintText.setAntiAlias(true);
//        paintText.setStyle(Paint.Style.STROKE);
//        paintText.setStrokeCap(Paint.Cap.SQUARE);
//        paintText.setStrokeWidth(paintWidth);

        paintText =new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(colorText);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStrokeCap(Paint.Cap.SQUARE);
        paintText.setTextSize(textContentSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = Math.min(width, height) / 2 - (paintWidth + 1) / 2;
        int centerX = width / 2;
        int centerY = height / 2;
        RectF oval = new RectF(centerX - r, centerY - r, centerX + r, centerY + r);
        canvas.drawArc(oval, -90, 360, false, paint);
        canvas.drawArc(oval, -90, 360 * progress, false, paintProgess);
//        paintProgess.setShader(null);
        if(textContent==null&&textContent.equals("")){
            return;
        }
        StaticLayout staticLayout = new StaticLayout(textContent, paintText, width - paintWidth - 1, Layout.Alignment.ALIGN_OPPOSITE, 1.0f, 0.0f, false);
        staticLayout.draw(canvas);
    }


}
