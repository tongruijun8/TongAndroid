package com.trj.tbase.dialog;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;

import com.trj.tbase.R;

import java.util.Date;

public abstract class BasePickView<T extends BasePickView.TSelectListenter> {

    protected Context context;

    //文字
    protected String titleText;
    protected String confirmText;
    protected String cancelText;

    //文字大小
    protected int textTitleSize;
    protected int textConfirmSize;
    protected int textCancelSize;
    protected int textSizeContent;

    //文字颜色
    protected int contentBgColor;
    protected int titleBgColor;
    protected int textSelectItemColor;
    protected int textNoSelectItemColor;
    protected int textTitleColor;
    protected int textConfirmColor;
    protected int textCancelColor;

    //分割线
    protected int dividerColor;
    protected boolean isAllLine;

    //其他
    protected boolean cancelable;
    protected float lineSpacingMultiplier;
    protected boolean isCenterLabel = false;



    public BasePickView(Context context) {
        this.context = context;
        titleText = "";
        confirmText = "";
        cancelText = "";
        textTitleSize = 18;
        textConfirmSize = 16;
        textCancelSize = 16;
        textSizeContent = 16;

        contentBgColor = R.color.dialog_contentBgColor;
        textSelectItemColor = R.color.dialog_textSelectItemColor;
        textNoSelectItemColor = R.color.dialog_textNoSelectItemColor;
        titleBgColor = R.color.dialog_titleBgColor;
        textTitleColor = R.color.dialog_textTitleColor;
        textConfirmColor = R.color.dialog_textConfirmColor;
        textCancelColor = R.color.dialog_textCancelColor;

        lineSpacingMultiplier = 2f;
        cancelable = true;//默认是可以取消
        isAllLine = true;//默认一整条线

        dividerColor = R.color.dialog_dividerColor; //分割线的颜色
    }

    //=================================-- 文字 --=================================

    /**
     * 设置弹框的标题文字
     *
     * @param titleText
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText == null ? "" : titleText;
    }

    /**
     * 设置弹框的确认按钮文字
     *
     * @param confirmText
     */
    public void setSubmitText(String confirmText) {
        this.confirmText = confirmText;
    }

    /**
     * 设置弹框的取消按钮文字
     *
     * @param cancelText
     */
    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    //=================================-- 文字大小 --=================================

    /**
     * 标题文字的大小
     *
     * @param textTitleSize
     */
    public void setTitleTextSize(int textTitleSize) {
        this.textTitleSize = textTitleSize;
    }

    /**
     * 取消按钮文字的大小
     *
     * @param textCancelSize
     */
    public void setCancleTextSize(int textCancelSize) {
        this.textCancelSize = textCancelSize;
    }

    /**
     * 确认按钮文字的大小
     *
     * @param textConfirmSize
     */
    public void setConfirmTextSize(int textConfirmSize) {
        this.textConfirmSize = textConfirmSize;
    }

    /**
     * 设置弹框的条目内容文字的大小
     *
     * @param textSizeContent
     */
    public void setContentTextSize(int textSizeContent) {
        this.textSizeContent = textSizeContent;
    }

    //=================================-- 颜色 --=================================


    /**
     * 设置内容的背景色
     *
     * @param contentBgColor  color res.
     */
    public void setContentBgColor(@ColorRes int contentBgColor) {
        this.contentBgColor = contentBgColor;
    }

    /**
     * 设置选中条目的颜色
     *
     * @param textSelectItemColor color res.
     */
    public void setTextSelectItemColor(@ColorRes int textSelectItemColor) {
        this.textSelectItemColor = textSelectItemColor;
    }

    /**
     * 设置未选中条目的颜色
     *
     * @param textNoSelectItemColor color res.
     */
    public void setTextNoSelectItemColor(@ColorRes int textNoSelectItemColor) {
        this.textNoSelectItemColor = textNoSelectItemColor;
    }

    /**
     * 设置未选中条目的颜色
     *
     * @param titleBgColor color res.
     */
    public void setTitleBgColor(@ColorRes int titleBgColor) {
        this.titleBgColor = titleBgColor;
    }

    /**
     * 设置标题的颜色
     *
     * @param textTitleColor color res.
     */
    public void setTitleColor(@ColorRes int textTitleColor) {
        this.textTitleColor = textTitleColor;
    }

    /**
     * 设置确认按钮的颜色
     *
     * @param textConfirmColor color res.
     */
    public void setSubmitColor(@ColorRes int textConfirmColor) {
        this.textConfirmColor = textConfirmColor;
    }

    /**
     * 设置取消按钮的颜色
     *
     * @param textCancelColor color res.
     */
    public void textColorCancel(@ColorRes int textCancelColor) {
        this.textCancelColor = textCancelColor;
    }

    //=================================-- 分割线相关属性 --=================================

    /**
     * 设置条目线条的类型
     *
     * @param isAllLine 是否是一整条线，true 代表一整条线，反之代表包裹条目的线条
     */
    public void setDividerType(boolean isAllLine) {
        this.isAllLine = isAllLine;
    }

    /**
     * 设置分割线的颜色
     *
     * @param dividerColor color resId.
     */
    public void setDividerColor(@ColorRes int dividerColor) {
        this.dividerColor = dividerColor;
    }

    //=================================-- 其他属性 --=================================

    /**
     * 设置是否点击弹框外取消
     *
     * @param cancelable 默认 true 可以取消；反之不可以取消
     */
    public void setOutSideCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    /**
     * 设置Item 的间距倍数，用于控制 Item 高度间隔
     *
     * @param lineSpacingMultiplier 浮点型，1.0-4.0f 之间有效,超过则取极值。
     */
    public void setLineSpacingMultiplier(@FloatRange(from = 1.0f, to = 4.0f) float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
    }

    /**
     * 设置label文字是否只显示中间的
     * @param isCenterLabel true 是；反之 每一行都显示
     */
    public void isCenterLabel(boolean isCenterLabel) {
        this.isCenterLabel = isCenterLabel;
    }


    /**
     * 此方法用于显示 dialog
     *
     * @param listenter
     */
    public abstract void showPickerView(T listenter);

    /**
     * 自定义的需要继承这个接口
     */
    public interface TSelectListenter {

    }

    public interface TSelectWeekListenter extends TSelectListenter {
        void onSelectWeek(String yearStr, String monthStr, String weekFirstDay, String weekEndDay);
    }

    public interface TSelectProvincesListenter extends TSelectListenter {
        void onSelectProvinces(ProvinceBean provinceBean, int itemposition2, int itemposition3);
    }

    public interface TSelectDateListenter extends TSelectListenter  {
        void onSelectDate(Date date);
    }
}
