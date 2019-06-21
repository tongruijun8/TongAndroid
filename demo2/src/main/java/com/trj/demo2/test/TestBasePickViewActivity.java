package com.trj.demo2.test;

import android.os.Bundle;
import android.view.View;

import com.trj.demo2.R;
import com.trj.tbase.activity.BaseActivity;
import com.trj.tbase.dialog.BasePickView;
import com.trj.tbase.dialog.ProvinceBean;
import com.trj.tbase.dialog.TSelectDateDialog;
import com.trj.tbase.dialog.TSelectDateWeekDialog;
import com.trj.tbase.dialog.TSelectProvincesDialog;
import com.trj.tlib.uils.DateTimeUtils;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.TUtils;
import com.trj.tlib.uils.ToastUtil;

import java.util.Calendar;
import java.util.Date;

public class TestBasePickViewActivity extends BaseActivity {

    TSelectDateDialog selectDateDialog;
    TSelectDateWeekDialog selectDateWeekDialog;

    TSelectProvincesDialog selectProvincesDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base_pick_view);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("选择框");

        selectDateDialog = new TSelectDateDialog(context);
        //也可以不设置：默认是1970-2100年
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(1900,0,1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2100,11,31);
        selectDateDialog.setData(startCalendar, endCalendar);
        selectDateDialog.setSelectData(Calendar.getInstance());

        selectDateWeekDialog = new TSelectDateWeekDialog(context);
        selectDateWeekDialog.setWeekPattern(DateTimeUtils.PATTERN_YMD_3);
        selectDateWeekDialog.setData(startCalendar,endCalendar);

        selectProvincesDialog = new TSelectProvincesDialog(context);
        selectProvincesDialog.setData(null);//使用默认数据

    }

    public void showDateDialog(View view) {
        selectDateDialog.showPickerView(new BasePickView.TSelectDateListenter() {
            @Override
            public void onSelectDate(Date date) {
                ToastUtil.showToast(context, TUtils.stampToDate(date.getTime()));
            }
        });
    }

    public void showWeekDateDialog(View view) {

        selectDateWeekDialog.showPickerView(new TSelectDateWeekDialog.TSelectWeekListenter() {
            @Override
            public void onSelectWeek(String yearStr, String monthStr,String weekFirstDay, String weekEndDay) {
                Logger.t("yearStr = "+yearStr);
                Logger.t("monthStr = "+monthStr);
                Logger.t("weekFirstDay = "+weekFirstDay);
                Logger.t("weekEndDay = "+weekEndDay);

                ToastUtil.showToast(context, weekFirstDay+" 到 " + weekEndDay);
            }
        });

    }

    public void showProvincesDateDialog(View view) {

        selectProvincesDialog.showPickerView(new BasePickView.TSelectProvincesListenter() {
            @Override
            public void onSelectProvinces(ProvinceBean provinceBean, int itemposition2, int itemposition3) {
                ToastUtil.showToast(context,provinceBean.getAreaName()+
                        provinceBean.getCities().get(itemposition2).getAreaName()+
                        provinceBean.getCities().get(itemposition2).getCounties().get(itemposition3).getAreaName()
                );
            }
        });

    }
}
