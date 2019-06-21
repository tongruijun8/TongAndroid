package com.trj.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trj.demo2.R;
import com.trj.tbase.adapter.TBaseAdapter;
import com.trj.tlib.uils.GlideUtile;

import java.util.List;

public class ImgAdapter extends TBaseAdapter<String> {


    public ImgAdapter(Context context, List<String> strings) {
        super(context, strings);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_img, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GlideUtile.bindImageView(context, tList.get(position), viewHolder.imageView);
        return convertView;
    }


    class ViewHolder {

        ImageView imageView;

        public ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.item_imageview);
        }
    }

}
