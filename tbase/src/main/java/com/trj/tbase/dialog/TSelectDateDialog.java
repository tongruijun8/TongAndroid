package com.trj.tbase.dialog;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.contrarywind.view.WheelView;
import com.trj.tlib.uils.Logger;

import java.util.Calendar;
import java.util.Date;

public class TSelectDateDialog extends BasePickView<BasePickView.TSelectDateListenter> {

    //默认年月日显示，时分秒不显示
    boolean[] timeType = new boolean[]{true, true, true, false, false, false};

    private String label_year;
    private String label_month;
    private String label_day;
    private String label_hours;
    private String label_minutes;
    private String label_seconds;

    private Calendar startCalendar;
    private Calendar endCalendar;
    private Calendar selectCalendar;

    private boolean cyclic;

    public TSelectDateDialog(Context context) {
        super(context);
        startCalendar = Calendar.getInstance();
        startCalendar.set(1970, 0, 1);
        endCalendar = Calendar.getInstance();
        endCalendar.set(2010, 11, 31);
        selectCalendar = Calendar.getInstance();
        titleText = "选择时间";
        label_year = "";
        label_month = "";
        label_day = "";
        label_hours = "";
        label_minutes = "";
        label_seconds = "";
        cyclic = false;
    }

    /**
     * &#x8bbe;&#x7f6e;&#x663e;&#x793a;&#x7684;&#x7c7b;&#x578b;
     *
     * @param timeType
     * @see  #getTimeType
     */
    @Deprecated
    public void setTimeType(boolean[] timeType) {
        if (timeType != null && timeType.length == 6) {
            this.timeType = timeType;
        }
    }

    /**
     * 获取时间列表类型：默认显示前三列（即年月日）
     * @return
     */
    public boolean[] getTimeType() {
        return timeType;
    }

    /**
     * 设置范围数据
     *
     * @param startCalendar
     * @param endCalendar
     */
    public void setData(Calendar startCalendar, Calendar endCalendar) {
        if (startCalendar != null) {
            this.startCalendar = startCalendar;
        }
        if (endCalendar != null) {
            this.endCalendar = endCalendar;
        }
    }

    /**
     * 设置选择的数据
     *
     * @param selectCalendar
     */
    public void setSelectData(Calendar selectCalendar) {
        if (selectCalendar != null) {
            this.selectCalendar = selectCalendar;
        }
    }

    /**
     * 设置label文字
     *
     * @param label_year
     * @param label_month
     * @param label_day
     * @param label_hours
     * @param label_mins
     * @param label_seconds
     */
    public void setLabel(String label_year, String label_month, String label_day, String label_hours, String label_mins, String label_seconds) {
        if (label_year != null) {
            this.label_year = label_year;
        }
        if (label_month != null) {
            this.label_month = label_month;
        }
        if (label_day != null) {
            this.label_day = label_day;
        }
        if (label_hours != null) {
            this.label_hours = label_hours;
        }
        if (label_minutes != null) {
            this.label_minutes = label_mins;
        }
        if (label_seconds != null) {
            this.label_seconds = label_seconds;
        }
    }

    /**
     * 设置循环状态
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        this.cyclic = cyclic;
    }

    public void showPickerView(final TSelectDateListenter listenter) {

        long startlon = startCalendar.getTimeInMillis();
        long endlon = endCalendar.getTimeInMillis();
        long selectlon = selectCalendar.getTimeInMillis();

        if (startlon >= endlon) {
            Logger.t("开始时间大于结束时间");
            return;
        }
        if (selectlon <= startlon) {
            selectCalendar.setTimeInMillis(startlon);
        } else if (selectlon >= endlon) {
            selectCalendar.setTimeInMillis(endlon);
        } else {
            selectCalendar.setTimeInMillis(selectlon);
        }

        new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (listenter != null) {
                    listenter.onSelectDate(date);
                }
            }
        })
                .setDate(selectCalendar)
                .setRangDate(startCalendar, endCalendar)
                .setType(timeType)
                .setTitleText(titleText)
                .setSubmitText(confirmText)
                .setCancelText(cancelText)
                .setTitleSize(textTitleSize)
                .setSubCalSize(textConfirmSize)
                .setContentTextSize(textSizeContent)
                .setTitleBgColor(context.getResources().getColor(titleBgColor))
                .setBgColor(context.getResources().getColor(contentBgColor))
                .setTextColorCenter(context.getResources().getColor(textSelectItemColor))
                .setTextColorOut(context.getResources().getColor(textNoSelectItemColor))
                .setTitleColor(context.getResources().getColor(textTitleColor))
                .setSubmitColor(context.getResources().getColor(textConfirmColor))
                .setCancelColor(context.getResources().getColor(textCancelColor))
                .setDividerColor(context.getResources().getColor(dividerColor))
                .setDividerType(isAllLine ? WheelView.DividerType.FILL : WheelView.DividerType.WRAP)
                .setOutSideCancelable(cancelable)
                .setLabel(label_year, label_month, label_day, label_hours, label_minutes, label_seconds) //设置空字符串以隐藏单位提示
                .setLineSpacingMultiplier(lineSpacingMultiplier)
                .isCyclic(cyclic)
                .isCenterLabel(isCenterLabel)
                .build()
                .show();
    }

//    public void showPickerView(final TSelectDateListenter listenter) {
//
//        long startlon = startCalendar.getTimeInMillis();
//        long endlon = endCalendar.getTimeInMillis();
//        long selectlon = selectCalendar.getTimeInMillis();
//
//        if (startlon >= endlon) {
//            Logger.t("开始时间大于结束时间");
//            return;
//        }
//        if (selectlon <= startlon) {
//            selectCalendar.setTimeInMillis(startlon);
//        } else if (selectlon >= endlon) {
//            selectCalendar.setTimeInMillis(endlon);
//        } else {
//            selectCalendar.setTimeInMillis(selectlon);
//        }
//
//        new TimePickerBuilder(context, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                if (listenter != null) {
//                    listenter.onSelectDate(date);
//                }
//            }
//        })
//                .setTitleText("时间选择")
//                .setDate(selectCalendar)
//                .setRangDate(startCalendar, endCalendar)
//                .setType(timeType)
//                .isCenterLabel(isCenterLabel) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabel(label_year, label_month, label_day, label_hours, label_minutes, label_seconds) //设置空字符串以隐藏单位提示   hide label
//                .setContentTextSize(16)//设置滚轮文字大小
//                .setDividerColor(context.getResources().getColor(R.color.dialog_zhuse))
//                .setDividerType(WheelView.DividerType.FILL)
//                .setTextColorCenter(context.getResources().getColor(R.color.dialog_zhuse))
//                .setBgColor(context.getResources().getColor(R.color.dialog_content_bg))
//                .setOutSideCancelable(false)
//                .build()
//                .show();
//    }

}
