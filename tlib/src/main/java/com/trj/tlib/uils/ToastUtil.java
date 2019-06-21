package com.trj.tlib.uils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ToastUtil {

    /**
     * 解决toast重复显示问题(不能直接用在子线程中，否则看不到效果)
     */

    private static Toast mToast = null;

    private static Handler mhandler = new Handler();

    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context context, String text, int duration) {
        if (duration == 0) {// 短时间
            duration = Toast.LENGTH_SHORT;
        } else {// 长时间
            duration = Toast.LENGTH_LONG;
        }
        mhandler.removeCallbacks(r);
        if (null != mToast) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context, text, duration);
        }
        mhandler.postDelayed(r, 3000);
        mToast.show();
    }

    public static void showToast(Context context, int strId, int duration) {
        showToast(context, context.getString(strId), duration);
    }

    public static void showToast(Context context, int strId) {
        showToast(context, strId+"", 0);
    }

    public static void showToast(Context context, String text) {
        showToast(context, text, 0);
    }
}
