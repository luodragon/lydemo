package com.lieying.lydemo3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lieying.lydemo3.R;

import java.util.ArrayList;
import java.util.List;

public class GvHomeAdapter extends BaseAdapter {
    private List<Integer> mockData = new ArrayList<>();
    Context context;

    public GvHomeAdapter(List<Integer> mockData, Context context) {
        this.mockData = mockData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mockData.size();
    }

    @Override
    public Object getItem(int i) {
        return mockData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.gv_home_item,null);
            holder.imgView = (ImageView)convertView.findViewById(R.id.img_zhibo);
            holder.textView = (TextView)convertView.findViewById(R.id.tv_zhibo);
            Glide.with(context).load(R.mipmap.zhubo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.imgView);
            convertView.setTag(holder);
        }else{
            convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        ImageView imgView;
        TextView textView;
    }
}
