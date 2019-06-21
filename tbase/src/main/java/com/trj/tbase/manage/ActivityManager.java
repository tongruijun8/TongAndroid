package com.trj.tbase.manage;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tencent.bugly.beta.Beta;
import com.trj.tbase.R;
import com.trj.tlib.uils.Logger;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *  activity管理类
 */
public class ActivityManager{
    private Set<AppCompatActivity> allActivity = new HashSet();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的ActivityManager实例
    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(AppCompatActivity activity) {
        allActivity.add(activity);
    }

    public void removeActivity(AppCompatActivity activity) {
        if (allActivity != null) {
            allActivity.remove(activity);
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (AppCompatActivity activity : allActivity) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 退出App：连续点击两次后退出
     * @param prompt 是否需要提示：true 双击退出；反之直接退出
     * @return
     */
    public void exitApp(boolean prompt) {
        if(prompt){
            if (exit) {
                exit();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        exit = true;
                        handler.sendEmptyMessageDelayed(1, 2000);
                    }
                }.start();
            }
        }else{
            exit();
        }

    }

    private boolean exit = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int whats = msg.what;
            if (whats == 1) {
                exit = false;
            }
        }
    };
//
//    public boolean exitAppDialog(AppCompatActivity activity) {
//
//        new TSelectBottomDialog.Builder(activity)
//                .setText("退出系统")
//                .setText("重新登录")
//                .setCancelable(false)
//                .setOnTdSelect(new BaseDialog.OnTdSelectListener() {
//                    @Override
//                    public void onTdSelect(int position) {
//                        if (position == 0) {
//
//                        }else if(position == 1){
//                            skipActivity(Login);
//                        }
//                    }
//                })
//                .create().show(activity.getSupportFragmentManager(),"exit");
//
//
//        return true;
//    }


    /**
     * 初始化状态栏字体的颜色
     * @param lightStatusBar true:状态栏字体为黑色；反之，状态栏字体为白色
     */
    public void setStateBarColor(boolean lightStatusBar, AppCompatActivity activity) {
        String brand = Build.MANUFACTURER.toLowerCase();
        Logger.t("--####--" + brand);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (brand.contains("xiaomi")) {
                if (lightStatusBar) {
                    Window window = activity.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    Window window = activity.getWindow();
                    int flag = window.getDecorView().getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    window.getDecorView().setSystemUiVisibility(flag);
                }
            } else if (brand.contains("oppo")) {
                //OPPO
                if (lightStatusBar) {
                    setOPPOStatusTextColor(true, activity);
                } else {
                    setOPPOStatusTextColor(false, activity);
                }
            } else {
                Window window = activity.getWindow();
                if(lightStatusBar){
                    window.setStatusBarColor(activity.getResources().getColor(R.color.color_white));
                }else{
                    window.setStatusBarColor(activity.getResources().getColor(R.color.color_black));
                }

            }
        }
    }

//    下面是调用状态栏 是否为darkmode。
    //miui7.7.13 版本后放弃
//    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
//        Class<? extends Window> clazz = activity.getWindow().getClass();
//        try {
//            int darkModeFlag = 0;
//            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//            darkModeFlag = field.getInt(layoutParams);
//            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 设置OPPO手机状态栏字体为黑色(colorOS3.0,6.0以下部分手机)
     *
     * @param lightStatusBar
     * @param activity
     */
    private void setOPPOStatusTextColor(boolean lightStatusBar, AppCompatActivity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int vis = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (lightStatusBar) {
                vis |= 0x00000010;
            } else {
                vis &= ~0x00000010;
            }
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }

    /**
     * buggly 的手动更新
     */
    public void updataApp(){
        Beta.checkUpgrade(true, false);
    }


//    public void registerEventBus(AppCompatActivity activity){
//        //注册订阅者
//        EventBus.getDefault().register(activity);
//    }
//    public void unregisterEventBus(AppCompatActivity activity){
//        //注销注册
//        EventBus.getDefault().unregister(activity);
//    }



}
