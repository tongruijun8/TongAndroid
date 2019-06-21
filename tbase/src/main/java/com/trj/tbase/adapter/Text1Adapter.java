package com.trj.tbase.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trj.tbase.R;

import java.util.List;

public class Text1Adapter extends TBaseAdapter<String> {

    private int gravity;

    public Text1Adapter(Context context, List<String> strings) {
        this(context, strings,Gravity.CENTER);
    }

    public Text1Adapter(Context context, List<String> strings, int gravity) {
        super(context, strings);
        this.gravity = gravity;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_text, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(tList.get(position));
        if (position < tList.size() - 1) {
            viewHolder.view.setVisibility(View.VISIBLE);
        }else{
            viewHolder.view.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {

        TextView textView;
        View view;

        public ViewHolder(View convertView) {
            textView = convertView.findViewById(R.id.item_text1);
            view = convertView.findViewById(R.id.item_text1_line);
            textView.setGravity(gravity);
        }
    }

}
