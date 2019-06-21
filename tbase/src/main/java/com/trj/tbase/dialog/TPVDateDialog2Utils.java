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
public class TPVDateDialog2Utils {

    private Context context;

    private Calendar calendar;

    public TPVDateDialog2Utils(Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
        getDayListData(calendar.get(Calendar.YEAR));
    }

    private OptionsPickerView optionsPickerViewLessons;

    private TLessonsListenter lessonsListenter;

    public TPVDateDialog2Utils create() {//条件选择器初始化，自定义布局
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
                    lessonsListenter.onLessonsText(dayBean.getYear(),dayBean.getMonth(), dayBean.getDay(),options2);
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
                                optionsPickerViewLessons.setNPicker(dayList, lessonsList,null);
                                optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsIndex);
                            }
                        });
                        ivRight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int yearNum = Integer.parseInt(tvTitle.getText().toString().trim());
                                tvTitle.setText(yearNum + 1 + "");
                                getDayListData(yearNum + 1);
                                optionsPickerViewLessons.setNPicker(dayList, lessonsList,null);
                                optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsIndex);
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

        optionsPickerViewLessons.setNPicker(dayList, lessonsList,null);
        optionsPickerViewLessons.setSelectOptions(dayListIndex, lessonsIndex);

        return this;

    }

    public void show(TLessonsListenter listener){
        this.lessonsListenter = listener;
        optionsPickerViewLessons.show();
    }

    public interface TLessonsListenter{
        void onLessonsText(String yearStr, String monthStr, String dayStr, int position);
    }


    private List<DayBean> dayList = new ArrayList<>();
    private int dayListIndex = -1;
//    private List<PitchLListInfo.PitchBean> lessonsList = new ArrayList<>();
    private List<String> lessonsList = new ArrayList<>();
    private int lessonsIndex = -1;

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

//    public TPVDateDialog2Utils setLessonsList(List<PitchLListInfo.PitchBean> lessonsList) {
//        this.lessonsList = lessonsList;
//        lessonsIndex = 0;
//        return this;
//    }

    public TPVDateDialog2Utils setLessonsList(List<String> lessonsList) {
        this.lessonsList.clear();
        this.lessonsList.addAll(lessonsList);
        lessonsIndex = 0;
        return this;
    }


}
