package com.lieying.lydemo3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lieying.lydemo3.R;
import com.lieying.lydemo3.bean.NewSpellActiveData;
import com.lieying.lydemo3.bean.NewSpellActiveProduct;

import java.util.ArrayList;
import java.util.List;

public class GvHotbuyAdapter extends BaseAdapter {

    private List<NewSpellActiveProduct> mockData = new ArrayList<>();
    Context context;
    String prefixUrl = "https://www.hzlyzb.com/img/";

    public GvHotbuyAdapter(ArrayList<NewSpellActiveProduct> data, Context context) {
        this.mockData = data;
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
        GvHotbuyAdapter.ViewHolder holder;
        if(convertView==null){
            holder = new GvHotbuyAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.gv_hotbuy_item,null);
            holder.imgView = (ImageView)convertView.findViewById(R.id.iv_hotbuy);
            Glide.with(holder.imgView).load(prefixUrl + mockData.get(i).getMinImage()).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(holder.imgView);
            holder.tvHotbuyName = (TextView)convertView.findViewById(R.id.tv_hotbuy_name);
            holder.priceView = (TextView)convertView.findViewById(R.id.tv_hotbuy_price);
            holder.addPriceView = (TextView)convertView.findViewById(R.id.tv_hotbuy_add_price);

            holder.tvHotbuyName.setText(mockData.get(i).getProductName());
            holder.priceView.setText("" + mockData.get(i).getSpellPrice());
            holder.imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int productId = mockData.get(i).getProductId();
                    ARouter.getInstance().build("/lydemo3/DetailsActivity").withInt("productId",productId).navigation();
                }
            });
            convertView.setTag(holder);
        }else{
            convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        ImageView imgView;
        TextView priceView;
        TextView addPriceView;
        TextView tvHotbuyName;
    }
}
