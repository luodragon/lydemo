package com.lieying.lydemo3.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.lieying.lydemo3.R;
import com.lieying.lydemo3.bean.Product;
import com.lieying.lydemo3.config.Config;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HotbuyRvAdapter extends RecyclerView.Adapter<HotbuyRvAdapter.ViewHolder> {
    private List<Product> fList = new ArrayList<>();
    private Context context;

    public HotbuyRvAdapter(ArrayList<Product> fList, Context context) {
        this.fList = fList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_hotbuy_item, parent,false);
        final ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      //  holder.tvOriginalPrice.setText(""+fList.get(position));
        String urlMinImg = Config.prefixUrl+fList.get(position).getMinImage();
        Log.e("minurl",urlMinImg);
        Glide.with(context).load(urlMinImg).into(holder.ivHappybuyPic);
        holder.tvOriginalPrice.setText(fList.get(position).getSkuPrice()+"");
        holder.tvHappybuyName.setText(fList.get(position).getProductName());
        holder.tvPriceJoinHappybuyPrice.setText(fList.get(position).getSpellPrice()+"");
        holder.tvHappybuyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productId = fList.get(position).getProductId();
                ARouter.getInstance().build("/lydemo3/DetailsActivity").withInt("productId",productId).navigation();

            }
        });
    }

    @Override
    public int getItemCount() {
        return fList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivHappybuyPic;
        TextView tvHappybuyName;
        TextView tvPecentNum;
        TextView tvJoinHappybuyNum;
        TextView tvOriginalPrice;
        TextView tvPriceJoinHappybuyPrice;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivHappybuyPic = itemView.findViewById(R.id.iv_happybuy);
            tvHappybuyName = itemView.findViewById(R.id.tv_happybuy_name);
            tvPecentNum = itemView.findViewById(R.id.tv_percent_num);
            tvJoinHappybuyNum = itemView.findViewById(R.id.tv_join_happybuy_num);
            tvOriginalPrice = itemView.findViewById(R.id.tv_original_price);
            tvOriginalPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            tvPriceJoinHappybuyPrice = itemView.findViewById(R.id.tv_price_join_happybuy);
        }

    }
}
