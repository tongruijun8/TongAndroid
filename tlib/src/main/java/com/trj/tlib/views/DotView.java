package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.trj.tlib.R;

public class DotView extends TBaseView {

    // 默认颜色
    private String defaultColor = "#D81B60";

    /**
     * 点的类型
     */
    private int dotType;

    /**
     * 点的大小
     */
    private int dotSize;

    /**
     * 空心点圆环的宽度
     */
    private int dotHollowWidth;
    /**
     * 线条的宽度
     */
    private int dotLineWidth;
    /**
     * 点的颜色
     */
    private int dotColor;
    /**
     * 线的颜色
     */
    private int dotColorLine;

    public void setDotType(int dotType) {
        this.dotType = dotType;
    }

    public void setDotSize(int dotSize) {
        this.dotSize = dotSize;
    }

    public void setDotHollowWidth(int dotHollowWidth) {
        this.dotHollowWidth = dotHollowWidth;
    }

    public void setDotLineWidth(int dotLineWidth) {
        this.dotLineWidth = dotLineWidth;
    }

    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
    }

    public void setDotColorLine(int dotColorLine) {
        this.dotColorLine = dotColorLine;
    }

    /**
     * 点的类型
     */
    public class DotType {
        /**
         * 空白的
         */
        public static final int DOT_TYPE_BLANK = 0x0000;
        /**
         * 实心点
         */
        public static final int DOT_TYPE_FILL = 0x0001;
        /**
         * 空心点
         */
        public static final int DOT_TYPE_HOLLOW = 0x0002;
        /**
         * 上部延伸线
         */
        public static final int DOT_TYPE_LINE_TOP = 0x0004;
        /**
         * 下部延伸线
         */
        public static final int DOT_TYPE_LINE_BOTTOM = 0x0008;
        /**
         * 左部延伸线
         */
        public static final int DOT_TYPE_LINE_LEFT = 0x0010;
        /**
         * 右部延伸线
         */
        public static final int DOT_TYPE_LINE_RIGHT = 0x0020;
        /**
         * 实心点:带水平线
         */
        public static final int DOT_TYPE_LINE_HORIZONTAL = DOT_TYPE_LINE_LEFT | DOT_TYPE_LINE_RIGHT;
        /**
         * 实心点:带垂直线
         */
        public static final int DOT_TYPE_LINE_VERTIACL = DOT_TYPE_LINE_TOP | DOT_TYPE_LINE_BOTTOM;

    }


    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initXmlAttribute(AttributeSet attrs) {
        TypedArray a = getTypedArray(attrs, R.styleable.DotView);
        dotSize = a.getDimensionPixelSize(R.styleable.DotView_dotview_size, 10);
        dotType = a.getInteger(R.styleable.DotView_dotview_type, DotType.DOT_TYPE_BLANK);
        dotHollowWidth = a.getDimensionPixelSize(R.styleable.DotView_dotview_hollow_width, 2);
        dotLineWidth = a.getDimensionPixelSize(R.styleable.DotView_dotview_line_size, 1);
        dotColor = a.getColor(R.styleable.DotView_dotview_color, Color.parseColor(defaultColor));
        dotColorLine = a.getColor(R.styleable.DotView_dotview_color_line, Color.parseColor(defaultColor));
        a.recycle();
    }

    @Override
    public void initAttribute() {
        dotSize = 10;
        dotType = DotType.DOT_TYPE_BLANK;
        dotHollowWidth = 2;
        dotLineWidth = 1;
        dotColor = Color.parseColor(defaultColor);
        dotColorLine = Color.parseColor(defaultColor);
    }

    //画笔
    private Paint paintFill;
    private Paint paintHollow;
    private Paint paintLine;

    @Override
    public void initPaint() {
        //实心圆的画笔
        paintFill = new Paint();
        paintFill.setColor(dotColor);
        paintFill.setAntiAlias(true);
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setStrokeCap(Paint.Cap.SQUARE);
        paintFill.setStrokeWidth(1);
        //空心圆的画笔
        paintHollow = new Paint();
        paintHollow.setColor(dotColor);
        paintHollow.setAntiAlias(true);
        paintHollow.setStyle(Paint.Style.STROKE);
        paintHollow.setStrokeCap(Paint.Cap.SQUARE);
        paintHollow.setStrokeWidth(dotHollowWidth);
        //线的画笔
        paintLine = new Paint();
        paintLine.setColor(dotColorLine);
        paintLine.setAntiAlias(true);
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setStrokeCap(Paint.Cap.SQUARE);
        paintLine.setStrokeWidth(dotLineWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int r = Math.min(width, height) / 2;
        int centerX = width / 2;
        int centerY = height / 2;

        //画点
        if (dotType > DotType.DOT_TYPE_BLANK) {
            //根据需求做相应的处理
            RectF oval = new RectF(centerX - dotSize, centerY - dotSize, centerX + dotSize, centerY + dotSize);
            if (dotType == DotType.DOT_TYPE_FILL) {
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == DotType.DOT_TYPE_HOLLOW) {
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_TOP)) {
                canvas.drawLine(centerX, centerY, centerX, 0, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_BOTTOM)) {
                canvas.drawLine(centerX, centerY, centerX, height, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_LEFT)) {
                canvas.drawLine(0, centerY, centerX, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_RIGHT)) {
                canvas.drawLine(width, centerY, centerX, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_HORIZONTAL)) {
                canvas.drawLine(0, centerY, width, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_FILL | DotType.DOT_TYPE_LINE_VERTIACL)) {
                canvas.drawLine(centerX, 0, centerX, height, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintFill);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_TOP)) {
                canvas.drawLine(centerX, centerY - dotSize, centerX, 0, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_BOTTOM)) {
                canvas.drawLine(centerX, centerY + dotSize, centerX, height, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_LEFT)) {
                canvas.drawLine(0, centerY, centerX - dotSize, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_RIGHT)) {
                canvas.drawLine(width, centerY, centerX + dotSize, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_HORIZONTAL)) {
                canvas.drawLine(0, centerY, centerX - dotSize, centerY, paintLine);
                canvas.drawLine(width, centerY, centerX + dotSize, centerY, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            } else if (dotType == (DotType.DOT_TYPE_HOLLOW | DotType.DOT_TYPE_LINE_VERTIACL)) {
                canvas.drawLine(centerX, centerY - dotSize, centerX, 0, paintLine);
                canvas.drawLine(centerX, centerY + dotSize, centerX, height, paintLine);
                canvas.drawArc(oval, 0, 360, false, paintHollow);
            }
        }
    }


}
