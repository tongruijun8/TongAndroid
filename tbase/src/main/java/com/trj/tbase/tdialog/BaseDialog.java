package com.trj.tbase.tdialog;

import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * 弹框：推荐使用
 *
 * @see TCommonDialog 一般的弹框包括：标题、内容布局 和 底部按钮
 * @see
 *
 *
 */
public class BaseDialog extends DialogFragment {

    /**
     * @see TCommonDialog
     *
     * 弹框按钮点击回调接口：用于取消按钮、确认按钮等
     */
    public interface OnTdfListener {
        /**
         * 点击回调的方法
         * @param view
         */
        boolean onTdClick(View view, String msg);
    }

    /**
     * @see TSelectBottomDialog
     */
    public interface OnTdSelectListener {
        /**
         * 点击回调的方法
         * @param position
         */
        void onTdSelect(int position);
    }


    /**
     *
     */
    public interface OnTdLookImgClickListener {
        /**
         * 点击回调的方法
         * @param position
         */
        void onTdImgClick();
    }


}
