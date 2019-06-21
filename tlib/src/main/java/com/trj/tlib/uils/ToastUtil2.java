package com.trj.tlib.uils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil2 {

    /**
     *
     * @param context
     * @param text
     * @param duration  Toast.LENGTH_SHORT，Toast.LENGTH_LONG
     */
    public static void showToast(Context context, String text, int duration) {
//        Toast.LENGTH_SHORT = 0;
////        Toast.LENGTH_LONG = 1;
        Toast.makeText(context, text, duration).show();
    }

    public static void showToast(Context context, String text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

}
