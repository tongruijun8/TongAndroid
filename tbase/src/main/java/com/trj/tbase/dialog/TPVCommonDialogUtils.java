package com.trj.tbase.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.trj.tbase.R;

import java.util.List;

public class TPVCommonDialogUtils {

    private Context context;
//    private int horizontalMargin;
    private int gravity;


    public TPVCommonDialogUtils(Context context) {
        this.context = context;
        gravity = Gravity.CENTER;
//        horizontalMargin = DensityUtil.dp2px(context, 20);
        initCustomOptionPicker();
    }

    private OptionsPickerView optionsPickerView;
    private TPVSelectListenter listenter;

    private OptionsPickerView initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        optionsPickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (listenter != null) {
                    listenter.onOptionsSelect(options1, options2, options3, v);
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_options_common, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        LinearLayout allLL = v.findViewById(R.id.all_ll);
                        TextView tvCancel = v.findViewById(R.id.tv_cancel);
                        TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerView.returnData();
                                optionsPickerView.dismiss();
                            }
                        });

                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                optionsPickerView.dismiss();
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
                .setDividerType(WheelView.DividerType.WRAP)
                .setTextColorCenter(context.getResources().getColor(R.color.dialog_zhuse))
                .setBgColor(context.getResources().getColor(R.color.dialog_content_bg))
//                .setLabels("", "至", "")
//                .isCenterLabel(true)
                .isDialog(true)
                .build();
        return optionsPickerView;
    }

    public void setList(List<String> optionsItems) {
        optionsPickerView.setPicker(optionsItems);
    }

    public void setNList(List<String> optionsItems1, List<String> optionsItems2, List<String> optionsItems3) {
        optionsPickerView.setNPicker(optionsItems1, optionsItems2, optionsItems3);
    }

    public void setSelectList(int option1) {
        optionsPickerView.setSelectOptions(option1);
    }

    public void setSelectList(int option1, int option2) {
        optionsPickerView.setSelectOptions(option1, option2);
    }

    public void setSelectList(int option1, int option2, int option3) {
        optionsPickerView.setSelectOptions(option1, option2, option3);
    }

    //ef：Gravity.CENTER
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

//    public void setHorizontalMargin(int horizontalMargin) {
//        this.horizontalMargin = DensityUtil.dp2px(context, horizontalMargin);
//    }

    public void show(TPVSelectListenter listenter){
        this.listenter = listenter;
        optionsPickerView.show();
        Dialog dialog = optionsPickerView.getDialog();
        //经测试，在这里设置背景色才起作用
        Window window = dialog.getWindow();
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setBackgroundDrawableResource(R.color.colorAccent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = gravity;
        lp.dimAmount = 0.4f;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //让宽度充满屏幕
//        lp.width = context.getResources().getDisplayMetrics().widthPixels - horizontalMargin;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
//        lp.dimAmount = builder.alpha;
//        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        lp.windowAnimations = com.bigkoo.pickerview.R.style.picker_view_scale_anim;
        window.setAttributes(lp);

    }


    public interface TPVSelectListenter {
        void onOptionsSelect(int options1, int options2, int options3, View v);
    }

}
