package com.example.caigouapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.caigouapp.R;
import com.example.caigouapp.data.RecipeBean;

import java.util.List;

public class RecipeSearchAdapter extends RecyclerView.Adapter<RecipeSearchAdapter.ViewHolder>
{
    private List<RecipeBean> recipeList;
    private Context context;

    public RecipeSearchAdapter(List<RecipeBean> recipeList, Context context)
    {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_search_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeSearchAdapter.ViewHolder holder, int position)
    {
        holder.name.setText(recipeList.get(position).getName()+" >");
        Glide.with(context).load(recipeList.get(position).getAvatar()).centerCrop().into(holder.avatar);
        holder.price.setText("￥"+recipeList.get(position).getPrice());
        holder.foodList.setText(recipeList.get(position).getFoodList());
    }

    @Override
    public int getItemCount()
    {
        return recipeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView avatar;
        TextView price;
        TextView foodList;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name = itemView.findViewById(R.id.tv_name);
            this.avatar = itemView.findViewById(R.id.iv_avatar);
            this.price = itemView.findViewById(R.id.tv_price);
            this.foodList = itemView.findViewById(R.id.tv_food_list);
        }
    }
}