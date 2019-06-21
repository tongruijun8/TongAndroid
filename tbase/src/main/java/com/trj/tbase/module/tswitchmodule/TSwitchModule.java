package com.trj.tbase.module.tswitchmodule;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tbase.R;

/**
 * @author tong
 * @date 2018/3/18 18:28
 */

public class TSwitchModule {

    private Context context;

    private TSwitchListenter listenter;

    private @ColorRes int backgroundColor;
    private @ColorRes int selectTextColor;
//    private @ColorRes int selectBackgroundColor;
    private @ColorRes int noSelectTextColor;
//    private @ColorRes int noSelectBackgroundColor;

    private String leftStr = "";
    private String rightStr = "";

    private boolean toggleState = false;


    public TSwitchModule(Context context) {
        this.context = context;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

//    public void setSelectBackgroundColor(int selectBackgroundColor) {
//        this.selectBackgroundColor = selectBackgroundColor;
//    }

    public void setNoSelectTextColor(int noSelectTextColor) {
        this.noSelectTextColor = noSelectTextColor;
    }

//    public void setNoSelectBackgroundColor(int noSelectBackgroundColor) {
//        this.noSelectBackgroundColor = noSelectBackgroundColor;
//    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }

    public void setToggle(boolean toggle) {
        this.toggleState = toggle;
    }

    public void setTSwitchListenter(TSwitchListenter listenter) {
        this.listenter = listenter;
    }

    public static class Builder {

        private Context context;
        private TSwitchInfo switchInfo;

        public Builder(Context context) {
            this.context = context;
            switchInfo = new TSwitchInfo();
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColor) {
            switchInfo.setBackgroundColor(backgroundColor);
            return this;
        }
        public Builder setSelectTextColor(@ColorRes int textColor) {
            switchInfo.setSelectTextColor(textColor);
            return this;
        }
        public Builder setNoSelectTextColor(@ColorRes int textColor) {
            switchInfo.setNoSelectTextColor(textColor);
            return this;
        }


//        public Builder setSelectBackgroundColor(@ColorRes int backgroundColor) {
//            switchInfo.setNoSelectTextColor(backgroundColor);
//            return this;
//        }
//        public Builder setNoSelectBackgroundColor(@ColorRes int backgroundColor) {
//            switchInfo.setNoSelectTextColor(backgroundColor);
//            return this;
//        }
        public Builder setLeftText(String leftText) {
            switchInfo.setLeftText(leftText);
            return this;
        }
        public Builder setRightText(String rightText) {
            switchInfo.setRightText(rightText);
            return this;
        }

        public Builder setToggle(boolean toggle) {
            switchInfo.setToggle(toggle);
            return this;
        }

        public TSwitchModule create(View rootView){
            TSwitchModule tSwitchModule = new TSwitchModule(context);
            tSwitchModule.setBackgroundColor(switchInfo.getBackgroundColor());
            tSwitchModule.setSelectTextColor(switchInfo.getSelectTextColor());
            tSwitchModule.setNoSelectTextColor(switchInfo.getNoSelectTextColor());
//            tSwitchModule.setSelectBackgroundColor(switchInfo.getSelectBackgroundColor());
//            tSwitchModule.setNoSelectBackgroundColor(switchInfo.getNoSelectBackgroundColor());
            tSwitchModule.setLeftStr(switchInfo.getLeftText());
            tSwitchModule.setRightStr(switchInfo.getRightText());
            tSwitchModule.setToggle(switchInfo.getToggle());
            tSwitchModule.init(rootView);
            return tSwitchModule;
        }



    }


    private RelativeLayout tswitch_rl;
    private LinearLayout tswitch_ll;
    private TextView lefttext;
    private TextView righttext;

    /**
     * 初始化
     */
    public void init(View rootView) {
        tswitch_rl = rootView.findViewById(R.id.tswitch_rl);
        tswitch_ll = rootView.findViewById(R.id.tswitch_ll);
        lefttext =  rootView.findViewById(R.id.tswitch_left);
        righttext =  rootView.findViewById(R.id.tswitch_right);

        if (backgroundColor > 0) {
            tswitch_rl.setBackgroundResource(backgroundColor);
        }
        lefttext.setText(leftStr);
        righttext.setText(rightStr);

        toggle(toggleState);


        lefttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle(false);
                if (null != listenter) {
                    listenter.onClickTSwitchLeft(v);
                }
            }
        });
        righttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle(true);
                if(null != listenter){
                    listenter.onClickTSwitchRight(v);
                }
            }
        });
    }

    public void toggle(boolean toggleState){
        if(toggleState){
            lefttext.setBackgroundResource(R.drawable.waikuang_r5_topswitch_left_);
            lefttext.setTextColor(noSelectTextColor > 0 ? context.getResources().getColor(noSelectTextColor) : Color.parseColor("#333333"));
            righttext.setBackgroundResource(R.drawable.waikuang_r5_topswitch_right_select);
            righttext.setTextColor(selectTextColor > 0 ? context.getResources().getColor(selectTextColor) : Color.parseColor("#ffffff"));
        }else{
            lefttext.setBackgroundResource(R.drawable.waikuang_r5_topswitch_left_select);
            lefttext.setTextColor(selectTextColor > 0 ? context.getResources().getColor(selectTextColor) : Color.parseColor("#ffffff"));
            righttext.setBackgroundResource(R.drawable.waikuang_r5_topswitch_right_);
            righttext.setTextColor(noSelectTextColor > 0 ? context.getResources().getColor(noSelectTextColor) : Color.parseColor("#333333"));
        }
    }

}
