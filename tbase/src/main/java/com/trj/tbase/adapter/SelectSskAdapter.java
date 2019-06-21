package com.trj.tbase.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trj.tbase.R;

import java.util.List;

/**
 * @author tong
 * @date 2018/2/9 14:27
 */

public class SelectSskAdapter extends TBaseAdapter<String> {

    private int index;
    private boolean itemSelectMark;

    public SelectSskAdapter(Context context, List<String> strings, int index, boolean itemSelectMark) {
        super(context, strings);
        this.index = index;
        this.itemSelectMark = itemSelectMark;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.layout_filter_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(tList.get(position));
        if(itemSelectMark){
            if(index == position){
                viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.color_ssk_item_bg_select));
            }else{
                viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.color_ssk_item_bg_));
            }
        }

        return convertView;
    }

    class ViewHolder {

        TextView textView;

        public ViewHolder(View convertView) {
            textView = convertView.findViewById(android.R.id.text1);
            textView.setMaxLines(2);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }


}
