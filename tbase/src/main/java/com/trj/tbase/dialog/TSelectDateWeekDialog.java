package com.trj.tbase.dialog;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.trj.tlib.uils.DateTimeUtils;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 对PickerView进行再次封装：适用于优途云的项目
 */
public class TSelectDateWeekDialog extends BasePickView<BasePickView.TSelectWeekListenter> {

    private String weekPattern;
    private String spaceChar;

    private String label_year;
    private String label_month ;
    private String label_week ;


    private boolean cyclic1;
    private boolean cyclic2;
    private boolean cyclic3;


    public TSelectDateWeekDialog(Context context) {
        super(context);
        cyclic1 = false;//默认不循环
        cyclic2 = false;//默认不循环
        cyclic3 = false;//默认不循环
        label_year = "";
        label_month = "";
        label_week = "";
        spaceChar = "-";
        titleText = "选择周";
    }

    /**
     * 设置label文字
     * @param label_year
     * @param label_month
     * @param label_day
     * @param label_hours
     * @param label_mins
     * @param label_seconds
     */
    public void setLabel(String label_year, String label_month, String label_week) {
        if(label_year!=null){
            this.label_year = label_year;
        }
        if(label_month!=null){
            this.label_month = label_month;
        }
        if(label_week!=null){
            this.label_week = label_week;
        }
    }

    /**
     * 设置循环状态
     * @param cyclic1 第一列是否循环
     * @param cyclic2 第二列是否循环
     * @param cyclic3 第三列是否循环
     */
    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        this.cyclic1 = cyclic1;
        this.cyclic2 = cyclic2;
        this.cyclic3 = cyclic3;
    }

    private List<DataWeekInfo> dataList = new ArrayList<>();
    private ArrayList<ArrayList<DataWeekInfo.MonthInfo>> monthDataList = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<DataWeekInfo.WeekInfo>>> weekDataList = new ArrayList<>();

    public void showPickerView(final TSelectWeekListenter listenter) {// 弹出选择器
        if (dataList == null && dataList.size() == 0) {
            ToastUtil.showToast(context,"请确认起始时间是否设置正确？");
            return;
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String yearStr = "";
                String monthStr = "";
                String weekFirstDay = "";
                String weekEndDay = "";

                try {
                    DataWeekInfo dataWeekInfo = dataList.get(options1);
                    DataWeekInfo.MonthInfo monthInfo = dataWeekInfo.getMonthList().get(options2);
                    DataWeekInfo.WeekInfo weekInfo = monthInfo.getWeekList().get(options3);
                    yearStr = dataWeekInfo.getYearName();
                    monthStr = monthInfo.getMonthName();
                    weekFirstDay = weekInfo.getStartWeekName();
                    weekEndDay = weekInfo.getEndWeekName();

                } catch (Exception e) {
                    yearStr = "";
                    monthStr = "";
                    weekFirstDay = "";
                    weekEndDay = "";
                } finally {
                    listenter.onSelectWeek(yearStr, monthStr, weekFirstDay, weekEndDay);
                }

            }
        })
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
                .setLabels(label_year, label_month, label_week)
                .setLineSpacingMultiplier(lineSpacingMultiplier)
                .setCyclic(cyclic1, cyclic2, cyclic3)
                .isCenterLabel(isCenterLabel)
                .build();

        pvOptions.setPicker(dataList, monthDataList, weekDataList);//三级选择器
        pvOptions.show();
    }


    /**
     * 设置周的显示方式
     * @param weekPattern
     * @see DateTimeUtils.PATTERN_YMD
     * @see DateTimeUtils.PATTERN_YMD_2
     * @see DateTimeUtils.PATTERN_YMD_3
     *
     * 或者自定义日期格式
     * 注：需要在setData之前设置才能起作用
     *
     */
    @SuppressWarnings("JavadocReference")
    public void setWeekPattern(String weekPattern) {
        this.weekPattern = weekPattern;
    }

    /**
     * 周之间的间隔符
     * @param spaceChar
     *
     * 注：需要在setData之前设置才能起作用
     */
    public void setSpaceChar(String spaceChar) {
        this.spaceChar = spaceChar == null ? "" : spaceChar;
    }

    public void setData(Calendar startCalendar, Calendar endCalendar) {

        if (weekPattern == null || weekPattern.equals("")) {
            weekPattern = DateTimeUtils.PATTERN_YMD;
        }

        if (startCalendar != null && endCalendar != null) {

            long startlon = startCalendar.getTimeInMillis();
            long endlon = endCalendar.getTimeInMillis();

            if (startlon < endlon) {
                jisuan(startCalendar, endCalendar);
            } else {
                Logger.t("开始时间大于结束时间");
            }

        } else {
            Logger.t("开始时间和结束时间不能为空");
        }

    }

    private void jisuan(Calendar startCalendar, Calendar endCalendar) {

        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH) + 1;
        int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH) + 1;
        int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

        for (int i = startYear; i <= endYear; i++) {
            //计算开始和结束的月份
            int startmm = 0;
            int endmm = 0;

            if (i == startYear) {
                startmm = startMonth;
                if (i == endYear) {
                    endmm = endMonth;
                } else {
                    endmm = 12;
                }

            } else if (i > startYear && i < endYear) {
                startmm = 1;
                endmm = 12;
            } else {
                startmm = 1;
                endmm = endMonth;
            }


            ArrayList<DataWeekInfo.MonthInfo> mmm = new ArrayList<>();
            ArrayList<ArrayList<DataWeekInfo.WeekInfo>> ddList = new ArrayList<>();
            //月份开始循环
            for (int j = startmm; j <= endmm; j++) {
                //计算开始和结束的天
                int startdd = 0;
                int enddd = 0;
                if (i == startYear) {
                    //事件范围内的第一年
                    if (i == endYear) {
                        if (j == startMonth) {
                            startdd = startDay;
                            if (j == endMonth) {//同年同月
                                enddd = endDay;
                            } else {
                                enddd = getMonthDay(i, j);
                            }
                        } else if (j == endMonth) {
                            startdd = 1;
                            enddd = endDay;
                        } else {
                            startdd = 1;
                            enddd = getMonthDay(i, j);
                        }

                    } else {
                        if (j == startMonth) {
                            startdd = startDay;
                            enddd = getMonthDay(i, j);
                        } else {
                            startdd = 1;
                            enddd = getMonthDay(i, j);
                        }
                    }
                } else if (i == endYear) {
                    //最后一年
                    startdd = 1;
                    if (j == endMonth) {
                        enddd = endDay;
                    } else {
                        enddd = getMonthDay(i, j);
                    }
                } else {
                    //中间的年份
                    startdd = 1;
                    enddd = getMonthDay(i, j);
                }

                ArrayList<DataWeekInfo.WeekInfo> weekInfoArrayList = new ArrayList<>();
                Calendar calendarW = Calendar.getInstance();
                calendarW.set(i, j - 1, startdd);
                int nextZhou = calendarW.get(Calendar.DAY_OF_WEEK);
                if (nextZhou > 1) {
                    nextZhou = 8 - nextZhou;
                }

                for (int k = startdd; k <= enddd; k = getNextWeekDay(enddd, k, nextZhou)) {
                    //把开始的天到结束的天之间转化为周显示
                    String[] dates = DateTimeUtils.convertWeekByDate(i, j - 1, k, weekPattern);
                    String dataStr = dates[0].substring(5) + spaceChar + dates[1].substring(5);
                    DataWeekInfo.WeekInfo weekInfo = new DataWeekInfo.WeekInfo();
                    weekInfo.setWeekName(dataStr);
                    weekInfo.setStartWeekName(dates[0]);
                    weekInfo.setEndWeekName(dates[1]);
                    weekInfoArrayList.add(weekInfo);
                }

                ddList.add(weekInfoArrayList);

                DataWeekInfo.MonthInfo monthInfo = new DataWeekInfo.MonthInfo();
                monthInfo.setMonthName(j < 10 ? "0" + j : j + "");
                monthInfo.setWeekList(weekInfoArrayList);
                mmm.add(monthInfo);
            }
            weekDataList.add(ddList);
            monthDataList.add(mmm);
            DataWeekInfo dataWeekInfo = new DataWeekInfo();
            dataWeekInfo.setYearName(i + "");
            dataWeekInfo.setMonthList(mmm);
            dataList.add(dataWeekInfo);
        }

    }

    private int getMonthDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private int getNextWeekDay(int enddd, int k, int nextZhou) {
        if (enddd < k + 7 && enddd >= k + nextZhou) {
            k = enddd;
        } else {
            k = k + 7;
        }
        return k;
    }

}
