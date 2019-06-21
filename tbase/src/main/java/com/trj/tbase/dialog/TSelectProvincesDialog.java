package com.trj.tbase.dialog;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trj.tlib.uils.AssestUtil;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TSelectProvincesDialog extends BasePickView<BasePickView.TSelectProvincesListenter> {

    private Gson gson;

    private List<ProvinceBean> provinceBeanList = new ArrayList<>();
    private ArrayList<ArrayList<ProvinceBean.CitiesBean>> citiesBeanList = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<ProvinceBean.CountiesBean>>> countiesBeanList = new ArrayList<>();

    private String label_year;
    private String label_month ;
    private String label_week ;

    private boolean cyclic1;
    private boolean cyclic2;
    private boolean cyclic3;

    public TSelectProvincesDialog(Context context) {
        super(context);
        gson = new Gson();
        cyclic1 = false;//默认不循环
        cyclic2 = false;//默认不循环
        cyclic3 = false;//默认不循环
        label_year = "";
        label_month = "";
        label_week = "";
        titleText = "选择省市区";
    }


    /**
     * 设置label文字
     * @param label_year
     * @param label_month
     * @param label_week
     */
    public void setLabel(String label_year, String label_month, String label_week) {
        if(label_year!=null){
            this.label_year = label_year;
        }
        if(label_month!=null){
            this.label_month = label_month;
        }
        if(label_week!=null){
            this.label_week = label_week;
        }
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        this.cyclic1 = cyclic1;
        this.cyclic2 = cyclic2;
        this.cyclic3 = cyclic3;
    }

    /**
     * 此json数据需要参考province.json 格式
     *
     * @param jsonString
     */
    public void setData(String jsonString) {

        if (jsonString == null || jsonString.equals("")) {
            jsonString = AssestUtil.getJson(context, "province.json");
        }
        provinceBeanList.addAll((ArrayList<ProvinceBean>) gson.fromJson(jsonString, new TypeToken<ArrayList<ProvinceBean>>() {
        }.getType()));

        if (provinceBeanList.size() > 0) {
            for (int i = 0; i < provinceBeanList.size(); i++) {//遍历省份
                ProvinceBean provinceBean = provinceBeanList.get(i);
                ArrayList<ArrayList<ProvinceBean.CountiesBean>> counList = new ArrayList<>();
                for (int c = 0; c < provinceBean.getCities().size(); c++) {//遍历该省份的所有城市
                    counList.add(provinceBean.getCities().get(c).getCounties());
                }
                /**
                 * 添加城市数据
                 */
                citiesBeanList.add(provinceBean.getCities());
                /**
                 * 添加地区数据
                 */
                countiesBeanList.add(counList);
            }
        }else{
            Logger.e("TSelectProvincesDialog","数据异常");
        }

    }


    public void showPickerView(final TSelectProvincesListenter listenter) {

        if (provinceBeanList.size() == 0) {
            ToastUtil.showToast(context,"未设置数据");
            return;
        }
        OptionsPickerView pickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (listenter != null) {
                    listenter.onSelectProvinces(provinceBeanList.get(options1),options2,options3);
                }
            }
        })
                .setTitleText(titleText)
                .setSubmitText(confirmText)
                .setCancelText(cancelText)
                .setTitleSize(textTitleSize)
                .setSubCalSize(textConfirmSize)
                .setContentTextSize(textSizeContent)
                .setTitleBgColor(context.getResources().getColor(titleBgColor))
                .setBgColor(context.getResources().getColor(contentBgColor))
                .setTextColorCenter(context.getResources().getColor(textSelectItemColor))
                .setTextColorOut(context.getResources().getColor(textNoSelectItemColor))
                .setTitleColor(context.getResources().getColor(textTitleColor))
                .setSubmitColor(context.getResources().getColor(textConfirmColor))
                .setCancelColor(context.getResources().getColor(textCancelColor))
                .setDividerColor(context.getResources().getColor(dividerColor))
                .setDividerType(isAllLine ? WheelView.DividerType.FILL : WheelView.DividerType.WRAP)
                .setOutSideCancelable(cancelable)
                .setLabels(label_year, label_month, label_week)
                .setLineSpacingMultiplier(lineSpacingMultiplier)
                .setCyclic(cyclic1, cyclic2, cyclic3)
                .isCenterLabel(isCenterLabel)
                .build();

        pickerView.setPicker(provinceBeanList, citiesBeanList, countiesBeanList);
        pickerView.show();

    }

}
