package com.trj.tbase.dialog;

import com.contrarywind.interfaces.IPickerViewData;

public class DayBean implements IPickerViewData {

    private int year;
    private int month;
    private int day;
    private int week;

    public DayBean() {
    }

    public DayBean(int year, int month, int day, int week) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
    }

    public String getYear() {
        return year+"";
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month < 10 ? "0" + month : "" + month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDay() {
        return day < 10 ? "0" + day : "" + day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeek() {
        return weekDay(week);
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public String getPickerViewText() {
        return month + "月" + day + "日 " + weekDay(week);
    }

    private String weekDay(int weekDay) {
        if (weekDay == 1) {
            return "周日";
        } else if (weekDay == 2) {
            return "周一";
        } else if (weekDay == 3) {
            return "周二";
        } else if (weekDay == 4) {
            return "周三";
        } else if (weekDay == 5) {
            return "周四";
        } else if (weekDay == 6) {
            return "周五";
        } else if (weekDay == 7) {
            return "周六";
        }
        return "";
    }

}
