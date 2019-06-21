package com.trj.tlib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.trj.tlib.R;
import com.trj.tlib.bean.SidebarInfo;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/5/6 15:56
 */
public class SidebarView2 extends TBaseView {

    /**
     * 底色
     */
    private int bgClickColor;
    private int textColor;
    private int textClickColor;
    private int textSize;

    private boolean showBkg = false;

    private List<SidebarInfo> sidebarBeanList;

    //最小分割单元个数：针对不同的高度可以灵活设置此值，来达到更好的效果
    private int minlen;//默认值为26

    private int len;
    private int toplen;

    public SidebarView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SidebarView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initAttribute() {
//        bgClickColor = Color.parseColor("#D81B60");
//        textColor = Color.parseColor("#D81B60");
//        textClickColor = Color.parseColor("#D81B60");
//        textSize = 6;
//        minlen = 26;
//        toplen = 0;
    }

    @Override
    public void initXmlAttribute(AttributeSet attrs) {
        TypedArray a = getTypedArray(attrs, R.styleable.SidebarView2);
        bgClickColor = a.getColor(R.styleable.SidebarView2_sv2_bg_click_color, Color.parseColor("#D81B60"));
        textColor = a.getColor(R.styleable.SidebarView2_sv2_text_color, Color.parseColor("#D81B60"));
        textClickColor = a.getColor(R.styleable.SidebarView2_sv2_text_click_color, Color.parseColor("#D81B60"));
        textSize = a.getDimensionPixelSize(R.styleable.SidebarView2_sv2_text_size, 6);
        minlen = a.getInt(R.styleable.SidebarView2_sv2_min_len, 26);
        a.recycle();
        toplen = 0;
    }

    private Paint paint;

    @Override
    public void initPaint() {
        paint = new Paint();
    }

    //    设置数据
    public void setListData(List<SidebarInfo> sidebarBeanList) {
        this.sidebarBeanList = sidebarBeanList;
        len = sidebarBeanList.size();
        //最小为minlen
        if (len < minlen) {
            toplen = (minlen - len) / 2;
            len = minlen;
        }
        refreshView();
    }


    private int choose = -1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            canvas.drawColor(bgClickColor);
        }
        if (sidebarBeanList != null) {
            int listSize = sidebarBeanList.size();
            if (listSize > 0) {
                int singleHeight = height / len;
                for (int i = toplen; i < toplen + listSize; i++) {
                    paint.setColor(textColor);
                    paint.setTextSize(textSize);
//            paint.setTypeface(Typeface.DEFAULT_BOLD);
                    paint.setAntiAlias(true);
                    if (i == choose) {
                        paint.setColor(textClickColor);
                        paint.setFakeBoldText(true);
                    }
                    String text = sidebarBeanList.get(i - toplen).getSidebarName();
                    float xPos = width / 2 - paint.measureText(text) / 2;
                    float yPos = singleHeight * i + singleHeight / 2 + paint.measureText(text) / 2;
                    canvas.drawText(text, xPos, yPos, paint);
                    paint.reset();
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        if (sidebarBeanList != null) {
            int listSize = sidebarBeanList.size();
            if (listSize > 0) {
                final int c = (int) (y / height * len);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        showBkg = true;
                        if (oldChoose != c && listener != null) {
                            if (c >= toplen && c < listSize + toplen) {
                                SidebarInfo sidebarBean = sidebarBeanList.get(c - toplen);
                                listener.onTouchingLetterChanged(sidebarBean.getSidebarName(), sidebarBean.getSidebarPosition());
                                choose = c;
                                invalidate();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (oldChoose != c && listener != null) {
                            if (c >= toplen && c < listSize + toplen) {
                                SidebarInfo sidebarBean = sidebarBeanList.get(c - toplen);
                                listener.onTouchingLetterChanged(sidebarBean.getSidebarName(), sidebarBean.getSidebarPosition());
                                choose = c;
                                invalidate();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        showBkg = false;
                        choose = -1;
                        invalidate();
                        break;
                }
            }
        }
        return true;
    }


    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener) {
        this.onTouchingLetterChangedListener = listener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s, int position);
    }

}
