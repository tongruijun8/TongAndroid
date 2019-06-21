package com.trj.tlib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.trj.tlib.uils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class SidebarView extends View {

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    //    private String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
//            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private List<SidebarBean> sidebarBeanList = new ArrayList<>(26);
    int choose = -1;
    private Paint paint = new Paint();
    private boolean showBkg = false;
    private Context context;
    private int textSize;
    private int textDefaultColor = Color.parseColor("#8c8c8c");
    private int textFocusColor = Color.parseColor("#40c60a");
    private int bgColor = Color.parseColor("#40000000");

    public SidebarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup(context);
    }

    public SidebarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public SidebarView(Context context) {
        super(context);
        setup(context);
    }

    public void setup(Context context) {
        this.context = context;
        textSize = DensityUtil.dp2px(context, 10);
        initData();
    }

    public List<SidebarBean> getSidebarBeanList() {
        return sidebarBeanList;
    }

    //    public void setSidebarBeanList(List<SidebarInfo> sidebarBeanList) {
//        this.sidebarBeanList = sidebarBeanList;
//    }
//
//    public void addData(SidebarInfo sidebarBean) {
//        this.sidebarBeanList.add(sidebarBean);
//    }

    private void initData(){
        String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        sidebarBeanList.clear();
        for (int i = 0; i < b.length; i++) {
            SidebarBean sidebarBean = new SidebarBean();
            sidebarBean.setName(b[i]);
            sidebarBean.setPosition(0);
            sidebarBeanList.add(sidebarBean);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            canvas.drawColor(bgColor);
        }
        int height = getHeight();
        int width = getWidth();
        if (sidebarBeanList.size() > 0) {
            int singleHeight = height / sidebarBeanList.size();
            for (int i = 0; i < sidebarBeanList.size(); i++) {
                paint.setColor(textDefaultColor);
                paint.setTextSize(textSize);
//            paint.setTypeface(Typeface.DEFAULT_BOLD);
                paint.setAntiAlias(true);
                if (i == choose) {
                    paint.setColor(textFocusColor);
                    paint.setFakeBoldText(true);
                }
                float xPos = width / 2 - paint.measureText(sidebarBeanList.get(i).getName()) / 2;
                float yPos = singleHeight * i + singleHeight;
                canvas.drawText(sidebarBeanList.get(i).getName(), xPos, yPos, paint);
                paint.reset();
            }

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        if (sidebarBeanList.size() > 0) {
            final int c = (int) (y / getHeight() * sidebarBeanList.size());
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    showBkg = true;
                    if (oldChoose != c && listener != null) {
                        if (c >= 0 && c < sidebarBeanList.size()) {
                            listener.onTouchingLetterChanged(sidebarBeanList.get(c).getName(), sidebarBeanList.get(c).getPosition());
                            choose = c;
                            invalidate();
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (oldChoose != c && listener != null) {
                        if (c >= 0 && c < sidebarBeanList.size()) {
                            listener.onTouchingLetterChanged(sidebarBeanList.get(c).getName(), sidebarBeanList.get(c).getPosition());
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
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener) {
        this.onTouchingLetterChangedListener = listener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s, int position);
    }


    @Deprecated
    public static class SidebarBean {

        private String name;
        private int position;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }


}
