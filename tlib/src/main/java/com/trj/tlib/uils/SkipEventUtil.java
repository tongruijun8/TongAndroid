package com.trj.tlib.uils;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class SkipEventUtil {

//    private static final String MARK = Build.MANUFACTURER.toLowerCase();
//    if (MARK.contains("huawei")) {
//    } else if (MARK.contains("xiaomi")) {
//    } else if (MARK.contains("oppo")) {
//    } else if (MARK.contains("vivo")) {
//    } else if (MARK.contains("meizu")) {

    //打开应用权限设置界面
    public void skipPermissionSettingPage(Context context) {
        context.startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

}
