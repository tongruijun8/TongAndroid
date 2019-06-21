package com.trj.tbase.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.trj.tbase.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 *  对PickerView进行再次封装：适用于优途云的项目
 */
public class TPVDateDialogUtils {

    private Context context;

    private Calendar calendar;

    public TPVDateDialogUtils(Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
        getDayListData(calendar.get(Calendar.YEAR));

        getHourListData();
        getMinuteListData();
        initCustomOptionPickerDate();

        getLessonsListData();
        initCustomOptionPickerLessons();

    }


    private OptionsPickerView optionsPickerViewDate;
    private OptionsPickerView optionsPickerViewLessons;

    private TDayListenter listener;
    private TLessonsListenter lessonsListenter;

    private OptionsPickerView initCustomOptionPickerDate() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        optionsPickerViewDate = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (listener != null) {
                    DayBean dayBean = dayList.get(options1);
                    listener.onDayText(dayBean.getYear(),dayBean.getMonth(), dayBean.getDay(),hourList.get(options2),minuteList.get(options3));
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_options_3, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        LinearLayout allLL = v.findViewById(R.id.all_ll);
                        final TextView tvTitle = v.findViewById(R.id.tv_title);
                        ImageView ivLeft = v.findViewById(R.id.iv_left);
                        ImageView ivRight = v.findViewById(R.id.iv_right);
                        TextView tvCancel = v.findViewById(R.id.tv_cancel);
                        TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        tvTitle.setText(calendar.get(Calendar.YEAR) + "");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerViewDate.returnData();
                                optionsPickerViewDate.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerViewDate.dismiss();
                            }
                        });

                        ivLeft.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int yearNum = Integer.parseInt(tvTitle.getText().toString().trim());
                                tvTitle.setText(yearNum - 1 + "");
                                getDayListData(yearNum - 1);
                                optionsPickerViewDate.setNPicker(dayList, hourList, minuteList);
                                optionsPickerViewDate.setSelectOptions(dayListIndex, hourListIndex, minuteListIndex);
                            }
                        });
                        ivRight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int yearNum = Integer.parseInt(tvTitle.getText().toString().trim());
                                tvTitle.setText(yearNum + 1 + "");
                                getDayListData(yearNum + 1);
                                optionsPickerViewDate.setNPicker(dayList, hourList, minuteList);
                                optionsPickerViewDate.setSelectOptions(dayListIndex, hourListIndex, minuteListIndex);
                            }
                        });
                        allLL.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }

                })
                .setContentTextSize(16)//设置滚轮文字大小
                .setOutSideCancelable(false)
                .setDividerColor(context.getResources().getColor(R.color.dialog_zhuse))
                .setTextColorCenter(context.getResources().getColor(R.color.dialog_zhuse))
                .setBgColor(context.getResources().getColor(R.color.dialog_content_bg))
//                .setLabels("", "至", "")
//                .isCenterLabel(true)
                .isDialog(true)
                .build();

        optionsPickerViewDate.setNPicker(dayList, hourList, minuteList);
        optionsPickerViewDate.setSelectOptions(dayListIndex, hourListIndex, minuteListIndex);

//        Dialog mDialog = optionsPickerViewDate.getDialog();
//        if (mDialog != null) {
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    Gravity.BOTTOM);
//            params.leftMargin = 0;
//            params.rightMargin = 0;
//            optionsPickerViewDate.getDialogContainerLayout().setLayoutParams(params);
//
//            Window dialogWindow = mDialog.getWindow();
//            if (dialogWindow != null) {
//                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
//                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
//            }
//        }

        return optionsPickerViewDate;

    }

    private OptionsPickerView initCustomOptionPickerLessons() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        optionsPickerViewLessons = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (lessonsListenter != null) {
                    DayBean dayBean = dayList.get(options1);
                    lessonsListenter.onLessonsText(dayBean.getYear(),dayBean.getMonth(), dayBean.getDay(),dayBean.getWeek(),lessonsList.get(options2).getLessons(),lessonsList2.get(options3).getLessons());
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_options_3, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        LinearLayout allLL = v.findViewById(R.id.all_ll);
                        final TextView tvTitle = v.findViewById(R.id.tv_title);
                        ImageView ivLeft = v.findViewById(R.id.iv_left);
                        ImageView ivRight = v.findViewById(R.id.iv_right);
                        TextView tvCancel = v.findViewById(R.id.tv_cancel);
                        TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        tvTitle.setText(calendar.get(Calendar.YEAR) + "");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerViewLessons.returnData();
                                optionsPickerViewLessons.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerViewLessons.dismiss();
                            }
                        });

                        ivLeft.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int yearNum = Integer.parseInt(tvTitle.getText().toString().trim());
                                tvTitle.setText(yearNum - 1 + "");
                                getDayListData(yearNum - 1);
                                optionsPickerViewLessons.setNPicker(dayList, lessonsList, lessonsList2);
                                optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsListIndex, lessonsListIndex2);
                            }
                        });
                        ivRight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int yearNum = Integer.parseInt(tvTitle.getText().toString().trim());
                                tvTitle.setText(yearNum + 1 + "");
                                getDayListData(yearNum + 1);
                                optionsPickerViewLessons.setNPicker(dayList, lessonsList, lessonsList2);
                                optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsListIndex, lessonsListIndex2);
                            }
                        });
                        allLL.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }

                })
                .setContentTextSize(16)//设置滚轮文字大小
                .setDividerColor(context.getResources().getColor(R.color.dialog_zhuse))
                .setDividerType(WheelView.DividerType.WRAP)
                .setTextColorCenter(context.getResources().getColor(R.color.dialog_zhuse))
                .setBgColor(context.getResources().getColor(R.color.dialog_content_bg))
                .setOutSideCancelable(false)
//                .setLabels("", "至", "")
//                .isCenterLabel(true)
                .isDialog(true)
                .build();

        optionsPickerViewLessons.setNPicker(dayList, lessonsList, lessonsList2);
        optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsListIndex, lessonsListIndex2);

        return optionsPickerViewLessons;

    }

    public void show(TDayListenter listener){
        this.listener = listener;
        optionsPickerViewDate.show();
    }

    public void showLessons(TLessonsListenter listener){
        this.lessonsListenter = listener;
        optionsPickerViewLessons.show();
    }

    public interface TDayListenter{
        void onDayText(String yearStr, String monthStr, String dayStr, String hourStr, String minuteStr);
    }
    public interface TLessonsListenter{
        void onLessonsText(String yearStr, String monthStr, String dayStr, String weekStr, int startLessons, int endLessons);
    }


    private List<DayBean> dayList = new ArrayList<>();
    private int dayListIndex = -1;
    private List<String> hourList = new ArrayList<>();
    private int hourListIndex = -1;
    private List<String> minuteList = new ArrayList<>();
    private int minuteListIndex = -1;
    private List<LessonsBean> lessonsList = new ArrayList<>();
    private int lessonsListIndex = -1;
    private List<LessonsBean> lessonsList2 = new ArrayList<>();
    private int lessonsListIndex2 = -1;


    private void getDayListData(int year) {

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(year, 0, 1);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(year+1, 0, 1);

        dayListIndex = -1;
        dayList.clear();
        while (calendarEnd.after(calendarStart)) {
            int mm = calendarStart.get(Calendar.MONTH) + 1;
            int dd = calendarStart.get(Calendar.DAY_OF_MONTH);
            int wkk = calendarStart.get(Calendar.DAY_OF_WEEK);
            dayList.add(new DayBean(year, mm, dd, wkk));
            if (mm < (calendar.get(Calendar.MONTH) + 1)) {
                dayListIndex++;
            }else if (mm == (calendar.get(Calendar.MONTH) + 1) && dd <= calendar.get(Calendar.DAY_OF_MONTH)) {
                dayListIndex++;
            }
            calendarStart.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private void getHourListData() {
        hourListIndex = -1;
        hourList.clear();
        for (int i = 0; i < 24; i++) {
            hourList.add(i < 10 ? ("0" + i) :  ("" + i) );
            if (i <= (calendar.get(Calendar.HOUR_OF_DAY))) {
                hourListIndex++;
            }
        }
    }

    private void getMinuteListData() {
        minuteListIndex = -1;
        minuteList.clear();
        for (int i = 0; i < 60; i++) {
            minuteList.add(i < 10 ? ("0" + i) :  ("" + i) );
            if (i <= (calendar.get(Calendar.MINUTE))) {
                minuteListIndex++;
            }
        }
    }

    private void getLessonsListData() {
        lessonsListIndex = 0;
        lessonsListIndex2 = 0;
        lessonsList.clear();
        lessonsList2.clear();
        for (int i = 1; i < 11; i++) {
            lessonsList.add(new LessonsBean(i));
            lessonsList2.add(new LessonsBean(i));
        }
    }

}
