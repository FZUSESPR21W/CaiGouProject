package com.example.caigouapp.ui.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.caigouapp.R;
import com.example.caigouapp.ui.RecipeDetailActivity;
import com.example.caigouapp.utils.GraphicUtil;
import com.example.caigouapp.utils.GsonUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    ArrayList<CustomerMenu> list;
    Context mContext;

    OrderDetailAdapter(Context context, ArrayList<CustomerMenu> list){
        this.mContext = context;
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImageView;
        TextView iconTextView;
        ChildPresenter customerMenuView;
        TextView priceView;
        ImageView showSourceMenuButton;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.img_order_detail_icon) ;
            iconTextView = itemView.findViewById(R.id.txt_order_detail_icon) ;
            priceView = itemView.findViewById(R.id.txt_order_detail_price) ;
            showSourceMenuButton = itemView.findViewById(R.id.btn_order_detail_show_source_menu) ;
            customerMenuView = itemView.findViewById(R.id.recycler_custom_menu) ;
            LinearLayoutManager lm = new LinearLayoutManager(itemView.getContext());
            lm.setOrientation(ChildPresenter.VERTICAL);
            customerMenuView.setLayoutManager(lm);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CustomerMenu cm = list.get(position);
        Glide.with(mContext)
                .load(cm.getPhotoUrl())
                //.placeholder() 这是等待时的图标
                .asBitmap()
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.iconImageView.setImageBitmap(resource);
                    }
                });
        //holder.iconImageView.setImageResource(cm.getiId());
        holder.iconTextView.setText(cm.getiName());//要根据返回的状态填入文字
        holder.priceView.setText("￥"+cm.getPrice());
        CustomerMenuAdapter adapter = new CustomerMenuAdapter(getMajorFoods(cm));
        holder.customerMenuView.setAdapter(adapter);
        holder.showSourceMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("id",cm.getSourceMenuId());
                mContext.startActivity(intent);
            }
        });
        //holder.showSourceMenuButton.setVisibility(View.GONE);
    }

    public List<Map<Food,String>> getMajorFoods(CustomerMenu cm){
        List<Map<Food,String>> foods = new ArrayList<>();
        String[] multiples = cm.getMultiple().split(",");
        for (int i = 0; i < cm.getFoods().size(); i++) {
            //GsonUtil.e("123",list.size()+"");
            if (multiples[i].equals("0"))
                continue;
            Food food = cm.getFoods().get(i);
            if(food.getMajor() == 1){
                HashMap<Food, String> map = new HashMap<Food, String>();
                map.put(food,multiples[i]);
                foods.add(map);
            }
        }
        return foods;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomerMenuAdapter extends ChildPresenter.Adapter<CustomerMenuAdapter.ViewHolders>{
        private List<Map<Food,String>> foods;
        private String multiple;
        class ViewHolders extends ChildPresenter.ViewHolder{
            TextView ingredientTextView;
            TextView multipleTextView;
            public ViewHolders(View itemView){
                super(itemView);
                ingredientTextView = itemView.findViewById(R.id.txt_customer_menu_ingredient);
                multipleTextView = itemView.findViewById(R.id.txt_customer_menu_multiple);
            }
        }

        public CustomerMenuAdapter(List<Map<Food,String>> foods){
            this.foods = foods;
        }

        @NonNull
        @NotNull
        @Override
        public ViewHolders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_menu_item,parent,false);
            ViewHolders holders=new ViewHolders(view);
            return holders;
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolders holder, int position) {
            Map<Food,String> map = foods.get(position);
            Food food = null;
            String multiple = null;
            for(Map.Entry<Food, String> entry : map.entrySet()){
                food = entry.getKey();
                multiple = entry.getValue();
            }
            String sw;
            sw = food.getStandardWeight() == null ? "?克" : food.getStandardWeight()+"克";

            holder.ingredientTextView.setText(food.getIngredient()+" "+sw);
            holder.multipleTextView.setText("×"+multiple);
        }

        @Override
        public int getItemCount(){
            return foods.size();
        }
    }
}