package com.trj.tbase.module.tswitchmodule;

public class TSwitchInfo {

    private int backgroundColor;
    private int selectTextColor;
//    private int selectBackgroundColor;
    private int noSelectTextColor;
//    private int noSelectBackgroundColor;

    private String leftText;
    private String rightText;

    private boolean toggle;

    public boolean getToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

//    public int getSelectBackgroundColor() {
//        return selectBackgroundColor;
//    }
//
//    public void setSelectBackgroundColor(int selectBackgroundColor) {
//        this.selectBackgroundColor = selectBackgroundColor;
//    }

    public int getNoSelectTextColor() {
        return noSelectTextColor;
    }

    public void setNoSelectTextColor(int noSelectTextColor) {
        this.noSelectTextColor = noSelectTextColor;
    }

//    public int getNoSelectBackgroundColor() {
//        return noSelectBackgroundColor;
//    }
//
//    public void setNoSelectBackgroundColor(int noSelectBackgroundColor) {
//        this.noSelectBackgroundColor = noSelectBackgroundColor;
//    }

    public String getLeftText() {
        return leftText == null ?"":leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText == null ?"":rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }
}
