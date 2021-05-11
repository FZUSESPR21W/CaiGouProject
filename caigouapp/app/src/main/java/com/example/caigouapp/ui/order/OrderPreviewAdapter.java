package com.unoni.login;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderPreviewAdapter extends RecyclerView.Adapter<OrderPreviewAdapter.ViewHolder> {
    List<Order> list;
    Context mContext;
    BaseAdapter mAdapter;

    OrderPreviewAdapter(Context context,List<Order> list){
        this.mContext = context;
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView storeNameView;
        TextView orderStateView;
        GridView oneOrderGridView;
        TextView priceView;
        TextView pieceView;
        Button cancelButton;
        ImageView showDetailButton;

        public ViewHolder(View itemView) {
            super(itemView);
            storeNameView = itemView.findViewById(R.id.txt_order_preview_store_name) ;
            orderStateView = itemView.findViewById(R.id.txt_order_preview_state) ;
            oneOrderGridView = itemView.findViewById(R.id.grid_order_preview) ;
            priceView = itemView.findViewById(R.id.txt_order_preview_price) ;
            pieceView = itemView.findViewById(R.id.txt_order_preview_piece) ;
            showDetailButton = itemView.findViewById(R.id.btn_order_preview_show_detail);
            cancelButton = itemView.findViewById(R.id.btn_order_preview_cancel) ;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_preview_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.showDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //开新活动，到OrderDetailActivity
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.storeNameView.setText(order.getStoreName());
        holder.orderStateView.setText(order.isOrder_state()+"");//要根据返回的状态填入文字
        setHorizontalGridView(position, holder.oneOrderGridView);
        holder.priceView.setText(order.getPrice()+"");
        holder.pieceView.setText("共"+order.getPiece()+"件");
        //holder.cancelButton.setText(order.getStoreName()); 根据订单状态决定文本，设置监听事件时也根据状态
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setHorizontalGridView(int position,GridView view) {
        ArrayList<Icon> icons = list.get(position).getIcons();
        int size = icons.size();
        int length = 100;
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        view.setColumnWidth(itemWidth); // 设置列表项宽
        view.setHorizontalSpacing(5); // 设置列表项水平间距
        view.setStretchMode(GridView.NO_STRETCH);
        view.setNumColumns(size); // 设置列数量=列表集合数

        mAdapter = new GridAdapter(icons, R.layout.item_grid_order) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                Icon icon = (Icon) obj;
                holder.setImageResource(R.id.img_grid, icon.getiId());
                holder.setText(R.id.txt_grid, icon.getiName());
            }
        };
        view.setAdapter(mAdapter);
    }
}