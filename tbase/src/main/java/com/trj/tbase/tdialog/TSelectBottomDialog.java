package com.trj.tbase.tdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.trj.tbase.R;
import com.trj.tbase.adapter.Text1Adapter;
import com.trj.tlib.views.TListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 底部选择弹框
 */
public class TSelectBottomDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.td_sb_cancle) {
            dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        builder.onTdSelectListener.onTdSelect(position);
        dismiss();
    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        protected float alpha;
        protected String cancleText;

        protected boolean cancelable;// 默认不可以取消

        protected List<String> stringList;

        private LayoutInflater inflater;

        protected OnTdSelectListener onTdSelectListener;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            alpha = 0.4f;
            cancelable = false;
            stringList = new ArrayList<>();
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setCancleText(String cancleText) {
            this.cancleText = cancleText;
            return this;
        }

        public Builder setText(String itemText) {
            if (itemText != null && !itemText.equals("")) {
                stringList.add(itemText);
            }
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setList(List<String> stringList) {
            if (stringList != null && stringList.size() > 0) {
                this.stringList.addAll(stringList);
            }
            return this;
        }

        public Builder setOnTdSelect(OnTdSelectListener onTdSelectListener) {
            this.onTdSelectListener = onTdSelectListener;
            return this;
        }

        public TSelectBottomDialog create() {
            TSelectBottomDialog dialog = new TSelectBottomDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.td_select_bottom, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            if (stringList.size() > 0) {
                Text1Adapter adapter = new Text1Adapter(context, stringList);
                dialog.viewHolder.tdSbSelect.setAdapter(adapter);
            }

            if (cancleText == null || cancleText.equals("")) {
                dialog.viewHolder.tdSbCancle.setText("取消");
            } else {
                dialog.viewHolder.tdSbCancle.setText(cancleText);
            }
            return dialog;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHolder.tdSbCancle.setOnClickListener(this);
        viewHolder.tdSbSelect.setOnItemClickListener(this);
        return contentView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置外部点击不取消
        if (!builder.cancelable) {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
        //经测试，在这里设置背景色才起作用
        Window window = getDialog().getWindow();
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setBackgroundDrawableResource(R.color.colorAccent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        //让宽度充满屏幕
        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.dimAmount = builder.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.DialogBottomAnimation;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        TListView tdSbSelect;
        TextView tdSbCancle;

        ViewHolder(View view) {
            this.view = view;
            tdSbSelect = view.findViewById(R.id.td_sb_select);
            tdSbCancle = view.findViewById(R.id.td_sb_cancle);

        }
    }

}
