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
import com.example.caigouapp.data.SearchResponse.MenusBean;

import java.util.List;

public class RecipeSearchAdapter extends RecyclerView.Adapter<RecipeSearchAdapter.ViewHolder>
{
    private List<MenusBean> menuList;
    private Context context;

    public RecipeSearchAdapter(List<MenusBean> menuList, Context context)
    {
        this.menuList = menuList;
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
        holder.name.setText(menuList.get(position).getName());
        Glide.with(context).load(menuList.get(position).getAvatar()).centerCrop().into(holder.avatar);
        holder.tag.setText(menuList.get(position).getTags());
    }

    @Override
    public int getItemCount()
    {
        return menuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView avatar;
        TextView tag;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name = itemView.findViewById(R.id.tv_name);
            this.avatar = itemView.findViewById(R.id.iv_avatar);
            this.tag = itemView.findViewById(R.id.tv_tag);
        }
    }
}