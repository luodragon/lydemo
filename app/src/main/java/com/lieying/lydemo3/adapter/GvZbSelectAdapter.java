package com.lieying.lydemo3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.lieying.lydemo3.R;

import java.util.ArrayList;
import java.util.List;

public class GvZbSelectAdapter extends BaseAdapter {
    private List<Integer> mockData = new ArrayList<>();
    Context context;

    public GvZbSelectAdapter(List<Integer> mockData, Context context) {
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
            convertView = View.inflate(context, R.layout.gv_zb_select_item,null);
            holder.imgView = (ImageView)convertView.findViewById(R.id.iv_zb_pic);
            holder.cbBox = (CheckBox) convertView.findViewById(R.id.cb_zb_select);
            convertView.setTag(holder);
        }else{
            convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        ImageView imgView;
        CheckBox cbBox;
    }
}
