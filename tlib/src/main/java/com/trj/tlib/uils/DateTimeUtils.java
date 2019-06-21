package com.trj.tlib.uils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static final String PATTERN_YMD = "yyyy-MM-dd";
    public static final String PATTERN_YMD_2 = "yyyy.MM.dd";
    public static final String PATTERN_YMD_3 = "yyyy/MM/dd";

    public static final String PATTERN_MD = "MM-dd";
    public static final String PATTERN_MD_2 = "MM.dd";
    public static final String PATTERN_MD_3 = "MM/dd";
    public static final String PATTERN_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YMD_HMS_2 = "yyyy.MM.dd HH:mm:ss";
    public static final String PATTERN_YMD_HMS_3 = "yyyy/MM/dd HH:mm:ss";

    public static String[] convertWeekByDate(Date time) {
        return convertWeekByDate(time, PATTERN_YMD);
    }
    public static String[] convertWeekByDate(Date time,String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return convertWeekByDate(cal,pattern);
    }

    public static String[] convertWeekByDate(int year, int month, int day) {
        return convertWeekByDate(year, month, day, PATTERN_YMD);
    }

    public static String[] convertWeekByDate(int year, int month, int day,String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return convertWeekByDate(cal,pattern);
    }



    public static String[] convertWeekByDate(Calendar todayCalendar,String pattern) {
        String result[] = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat(pattern); //设置时间格式
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = todayCalendar.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            todayCalendar.add(Calendar.DAY_OF_MONTH, -1);
        }
//        System.out.println("要计算日期为:"+sdf.format(todayCalendar.getTime())); //输出要计算日期
        todayCalendar.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = todayCalendar.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        todayCalendar.add(Calendar.DATE, todayCalendar.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(todayCalendar.getTime());
//        System.out.println("所在周星期一的日期："+imptimeBegin);
        todayCalendar.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(todayCalendar.getTime());
//        System.out.println("所在周星期天的日期："+imptimeEnd);
        result[0] = imptimeBegin;
        result[1] = imptimeEnd;
        return result;

    }

    /**
     * 格式化时间
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
