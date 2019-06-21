package com.trj.tbase.dialog;

import java.util.List;

public class DataWeekInfo {

    private String yearName;

    private List<MonthInfo> monthList;


    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public List<MonthInfo> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<MonthInfo> monthList) {
        this.monthList = monthList;
    }


    @Override
    public String toString() {
        return yearName;
    }

    public static class MonthInfo{

        private String monthName;

        private List<WeekInfo> weekList;

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }

        public List<WeekInfo> getWeekList() {
            return weekList;
        }

        public void setWeekList(List<WeekInfo> weekList) {
            this.weekList = weekList;
        }

        @Override
        public String toString() {
            return monthName;
        }
    }



    public static class WeekInfo{

        private String weekName;

        private String startWeekName;
        private String endWeekName;

        public String getStartWeekName() {
            return startWeekName;
        }

        public void setStartWeekName(String startWeekName) {
            this.startWeekName = startWeekName;
        }

        public String getEndWeekName() {
            return endWeekName;
        }

        public void setEndWeekName(String endWeekName) {
            this.endWeekName = endWeekName;
        }

        public String getWeekName() {
            return weekName;
        }

        public void setWeekName(String weekName) {
            this.weekName = weekName;
        }


        @Override
        public String toString() {
            return weekName;
        }

    }




}
