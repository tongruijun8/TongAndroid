package com.trj.tlib.uils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class IntentUtil {

//    private static final String MARK = Build.MANUFACTURER.toLowerCase();
//    if (MARK.contains("huawei")) {
//    } else if (MARK.contains("xiaomi")) {
//    } else if (MARK.contains("oppo")) {
//    } else if (MARK.contains("vivo")) {
//    } else if (MARK.contains("meizu")) {


    /**
     * 打开设置界面
     * @param context
     */
    public void skipPermissionSettingPage(Context context) {
        context.startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    /**
     * 打电话
     * @param context 上下文
     * @param phoneNum 电话号码
     */
    public void callPhoneNumber(Context context,String phoneNum) {
        //我们需要告诉系统，我们的动作：我要打电话
        //创建意图对象
        Intent intent = new Intent();
        //把打电话的动作ACTION_CALL封装至意图对象当中
        intent.setAction(Intent.ACTION_CALL);
        //设置打给谁
        intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”

        //把动作告诉系统,启动系统打电话功能。
        context.startActivity(intent);
    }

}
