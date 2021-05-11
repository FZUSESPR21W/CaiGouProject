package com.example.caigouapp.ui.shopping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.ui.RecipeDetailActivity;
import com.example.caigouapp.ui.adapter.PersonalIngredientAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    private List<RecipeBean> list = new ArrayList<>();
    private Map<Integer, Boolean> map = new TreeMap<>();
    public boolean open = false;
    private Context mContext;

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public ShoppingAdapter(List<RecipeBean> list, Context context){
        this.list.addAll(list);
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeBean recipeBean = list.get(position);
        String str = "￥"+recipeBean.getPrice();
        holder.recipeImage.setImageResource(R.mipmap.ic_launcher_round);
        if(open){
            holder.showButton.setVisibility(View.GONE);
            holder.recipeCheck.setVisibility(View.VISIBLE);
            holder.recipeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        map.put(position, true);
                    } else {
                        map.remove(position);
                    }
                }
            });
            holder.recipeCheck.setChecked(map != null && map.containsKey(position));
        }
        else {
            holder.recipeCheck.setVisibility(View.GONE);
            holder.showButton.setVisibility(View.VISIBLE);
            map.clear();
        }
        holder.recipeName.setText(recipeBean.getName());
        holder.recipeIntro.setText(recipeBean.getIntro());
        holder.recipePrice.setText(str);
        holder.recipeIngredient.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recipeIngredient.setAdapter(new PersonalIngredientAdapter((ArrayList<Ingredient>) recipeBean.getIngredient()));
        //holder.recipeIngredient.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        holder.recipeIngredient.setVisibility(View.GONE);
        holder.showButton.setImageResource(R.drawable.arrow_down);
        holder.endItem.setVisibility(View.GONE);
        holder.showButton.setTag("close");
        holder.showButton.setOnClickListener(view -> {
            if(holder.showButton.getTag().equals("close")){
                holder.showButton.setTag("open");
                holder.recipeIngredient.setVisibility(View.VISIBLE);
                holder.showButton.setImageResource(R.drawable.arrow_up);
                holder.endItem.setVisibility(View.VISIBLE);
            }
            else if(holder.showButton.getTag().equals("open")){
                holder.showButton.setTag("close");
                holder.recipeIngredient.setVisibility(View.GONE);
                holder.showButton.setImageResource(R.drawable.arrow_down);
                holder.endItem.setVisibility(View.GONE);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, RecipeDetailActivity.class);
            intent.putExtra("recipe",list.get(position));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public void delete(){
        int i = 0;
        for (int position:map.keySet()) {
            list.remove(position-i);
            i++;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView recipeImage;
        TextView recipeName;
        TextView recipeIntro;
        TextView recipePrice;
        CheckBox recipeCheck;
        RecyclerView recipeIngredient;
        ImageView showButton;
        TextView endItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = (ImageView)itemView.findViewById(R.id.shoppingRecipeImage);
            recipeName = (TextView)itemView.findViewById(R.id.shoppingRecipeName);
            recipeIntro = (TextView)itemView.findViewById(R.id.shoppingRecipeIntro);
            recipePrice = (TextView)itemView.findViewById(R.id.shoppingRecipePrice);
            recipeCheck = (CheckBox)itemView.findViewById(R.id.shoppingRecipeCheck);
            recipeIngredient = (RecyclerView)itemView.findViewById(R.id.personalIngredientRv);
            showButton = (ImageView)itemView.findViewById(R.id.showButton);
            endItem = (TextView)itemView.findViewById(R.id.endItem);
        }
    }
}

