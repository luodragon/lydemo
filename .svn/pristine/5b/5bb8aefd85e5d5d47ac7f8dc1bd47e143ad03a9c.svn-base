package com.lieying.lydemo3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lieying.lydemo3.R;

import java.util.List;

public class ChatDialogAdapter extends RecyclerView.Adapter<ChatDialogAdapter.ViewHolder> {
    List<Integer> list;
    Context mContext;

    public ChatDialogAdapter(List<Integer> list,Context context)  {
    this.list = list;
    this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_chat_dialog_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_header_pic;
        TextView tv_username;
        TextView tv_chat_message;
        ImageView iv_redheart;
        TextView tv_redheart_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_header_pic = itemView.findViewById(R.id.iv_header_pic);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_chat_message = itemView.findViewById(R.id.tv_chat_message);
            iv_redheart = itemView.findViewById(R.id.iv_redheart);
            tv_redheart_num = itemView.findViewById(R.id.tv_redheart_num);
        }
    }
}
