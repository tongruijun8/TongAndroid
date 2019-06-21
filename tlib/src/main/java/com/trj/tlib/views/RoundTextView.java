package com.trj.tlib.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.widget.TextView;

import com.trj.tlib.R;

@SuppressLint("AppCompatCustomView")
public class RoundTextView extends TextView {

    protected int width = 0;
    protected int height = 0;

    //画笔
    private Paint paint;
    private Paint paintProgess;

    //圆环的进度计数
    @FloatRange(from = 0.0, to = 1.0)
    private float progress = 0;
    //    画笔的宽度
    private int paintWidth = 20;
    //  背景色
    private int colorBg;
    //    前置色
    private int colorProgress;
    //  是否显示圆环（默认显示）
    private boolean isShowRound = true;


    public RoundTextView(Context context) {
        super(context);
        initAttribute();
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        paintWidth = a.getInteger(R.styleable.RoundTextView_rtextview_width, 20);
        progress = a.getFloat(R.styleable.RoundTextView_rtextview_progress, 0.0f);
        colorBg = a.getColor(R.styleable.RoundTextView_rtextview_bg_color, Color.parseColor("#333333"));
        colorProgress = a.getColor(R.styleable.RoundTextView_rtextview_progress_color, Color.parseColor("#aa3399"));
        isShowRound = a.getBoolean(R.styleable.RoundTextView_rtextview_show, true);
        a.recycle();
    }

    private void initAttribute() {
        paintWidth = 20;
        progress = 0.0f;
        isShowRound = true;
        colorBg = Color.parseColor("#333333");
        colorProgress = Color.parseColor("#aa3399");
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setPaintWidth(int paintWidth) {
        this.paintWidth = paintWidth;
    }

    public void setColorBg(int colorBg) {
        this.colorBg = colorBg;
    }

    public void setColorProgress(int colorProgress) {
        this.colorProgress = colorProgress;
    }

    public void setShowRound(boolean showRound) {
        isShowRound = showRound;
    }

    /**
     * 刷新控件
     */
    public void refresh() {
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
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeWidth(paintWidth);

        paintProgess = new Paint();
        paintProgess.setColor(colorProgress);
        paintProgess.setStyle(Paint.Style.STROKE);
        paintProgess.setStrokeCap(Paint.Cap.ROUND);
        paintProgess.setStrokeWidth(paintWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isShowRound){
            int r = Math.min(width, height) / 2 - (paintWidth + 1) / 2;
            int centerX = width / 2;
            int centerY = height / 2;
            RectF oval = new RectF(centerX - r, centerY - r, centerX + r, centerY + r);
            canvas.drawArc(oval, -90, 360, false, paint);
            canvas.drawArc(oval, -90, 360 * progress, false, paintProgess);
        }
    }

}
