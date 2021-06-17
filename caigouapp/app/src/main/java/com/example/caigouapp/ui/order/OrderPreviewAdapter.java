package com.example.caigouapp.ui.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
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

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.caigouapp.R;
import com.example.caigouapp.data.CancelOrderResponse;
import com.example.caigouapp.data.OrderResponse;
import com.example.caigouapp.ui.RecipeDetailActivity;
import com.example.caigouapp.utils.GsonUtil;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        holder.showDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                intent.putExtra("data", new Gson().toJson(list.get((int)view.getTag())));
                mContext.startActivity(intent);
            }
        });
        //如果不是已下单状态,则将文本改为"再来一单"
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.storeNameView.setText(order.getStoreName());
        holder.orderStateView.setText(order.getOrderStateString());
        setHorizontalGridView(position, holder.oneOrderGridView);
        holder.priceView.setText("￥"+order.getPrice());
        holder.pieceView.setText("共 "+order.getCms().size()+" 件");
        holder.cancelButton.setText(getCancelButtonText(order));
        holder.showDetailButton.setTag(position);
        //GsonUtil.e("1333",order.toString());

        if(order.getOrderState() == 3 || order.getOrderState() == 4){
            holder.cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Integer> map = new HashMap<String, Integer>();
                    map.put("orderId", Integer.valueOf(order.getOrderNumber()));
                    GsonUtil.e("1333",order.getOrderNumber());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://106.53.148.37:8082/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    OrderRequest request = retrofit.create(OrderRequest.class);
                    Call<CancelOrderResponse> call = request.cancelOrder(requestBody);
                    GsonUtil.e("1333",call.request().url().toString());
                    call.enqueue(new Callback<CancelOrderResponse>() {
                        @Override
                        public void onResponse(Call<CancelOrderResponse> call, Response<CancelOrderResponse> response) {
                            String str = new Gson().toJson(response.body());
                            GsonUtil.e("1333",response.body().toString());
                            try{
                                JSONObject jsonObject = new JSONObject(str);
                                int code = jsonObject.getInt("code");
                                GsonUtil.e("1333",code+"");
                                if(code == 200) Toast.makeText(mContext,"取消订单成功！",Toast.LENGTH_SHORT).show();
                                holder.orderStateView.setText("已取消");
                                holder.cancelButton.setText("再来一单");
                                Order temp = order;
                                temp.setOrderState(2);
                                list.set(position,temp);
                                GsonUtil.e("1333",list.get(position).toString());
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<CancelOrderResponse> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getCancelButtonText(Order order){
        if(order.getOrderState()!=3 && order.getOrderState()!=4){
            return "再来一单";
        }
        return "取消订单";
    }

    public void setHorizontalGridView(int position,GridView view) {
        ArrayList<CustomerMenu> cms = list.get(position).getCms();
        int size = cms.size();
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

        mAdapter = new GridAdapter(cms, R.layout.item_grid_order) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                CustomerMenu cm = (CustomerMenu) obj;
                Glide.with(mContext)
                        .load(cm.getPhotoUrl())
                        //.placeholder() 这是等待时的图标
                        .asBitmap()
                        .fitCenter()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                holder.setImageResource(R.id.img_grid,resource);
                            }
                        });
                holder.getItemView().setOnClickListener(v -> {
                        Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                        intent.putExtra("id", cm.getSourceMenuId());
                        mContext.startActivity(intent);
                });
                //holder.setImageResource(R.id.img_grid, R.drawable.sample);//这里要用加载图片的框架
                holder.setText(R.id.txt_grid, cm.getiName());
            }

        };
        view.setAdapter(mAdapter);
    }
}