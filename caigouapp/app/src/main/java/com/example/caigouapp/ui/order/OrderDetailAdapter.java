package com.unoni.login;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    ArrayList<Icon> list;

    OrderDetailAdapter(ArrayList<Icon> list){
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImageView;
        TextView iconTextView;
        TextView methodView;
        TextView priceView;
        ImageView showSourceMenuButton;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.img_order_detail_icon) ;
            iconTextView = itemView.findViewById(R.id.txt_order_detail_icon) ;
            methodView = itemView.findViewById(R.id.txt_order_detail_method) ;
            priceView = itemView.findViewById(R.id.txt_order_detail_price) ;
            showSourceMenuButton = itemView.findViewById(R.id.btn_order_detail_show_source_menu) ;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.showSourceMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开新活动，到源菜谱详情界面
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Icon icon = list.get(position);
        holder.iconImageView.setImageResource(icon.getiId());
        holder.iconTextView.setText(icon.getiName());//要根据返回的状态填入文字
        holder.priceView.setText(icon.getPrice()+"");
        holder.methodView.setText(icon.getMethod());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}