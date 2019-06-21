package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.trj.tlib.R;
import com.trj.tlib.uils.Logger;

/**
 * 可以设置圆角的ImageView
 */
public class TRoundImageView extends AppCompatImageView {

    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    protected int width = 0;
    protected int height = 0;

    private int defaultRadius = 0;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;

    public TRoundImageView(Context context) {
        super(context);
        init(context, null);
    }

    public TRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TRoundImageView);
        radius = array.getDimensionPixelOffset(R.styleable.TRoundImageView_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.TRoundImageView_left_top_radius, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.TRoundImageView_right_top_radius, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.TRoundImageView_right_bottom_radius, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.TRoundImageView_left_bottom_radius, defaultRadius);

        Logger.t("-----###----radius = "+radius);
        Logger.t("-----###----leftTopRadius = "+leftTopRadius);
        Logger.t("-----###----rightTopRadius = "+rightTopRadius);
        Logger.t("-----###----rightBottomRadius = "+rightBottomRadius);
        Logger.t("-----###----leftBottomRadius = "+leftBottomRadius);

        //如果四个角的值没有设置，那么就使用通用的radius的值。
        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius;
        }
        array.recycle();

//        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
//        setLayerType(View.LAYER_TYPE_HARDWARE, null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Logger.t("-----###----width = "+width);
        Logger.t("-----###----height = "+height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;

        if (width >= minWidth && height >= minHeight) {
            Path path = new Path();
            //四个角：右上，右下，左下，左上
            path.moveTo(leftTopRadius, 0);
            path.lineTo(width - rightTopRadius, 0);
            path.quadTo(width, 0, width, rightTopRadius);

            path.lineTo(width, height - rightBottomRadius);
            path.quadTo(width, height, width - rightBottomRadius, height);

            path.lineTo(leftBottomRadius, height);
            path.quadTo(0, height, 0, height - leftBottomRadius);

            path.lineTo(0, leftTopRadius);
            path.quadTo(0, 0, leftTopRadius, 0);

//            canvas.setDrawFilter(paintFlagsDrawFilter);
//            canvas.save();
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }


}
