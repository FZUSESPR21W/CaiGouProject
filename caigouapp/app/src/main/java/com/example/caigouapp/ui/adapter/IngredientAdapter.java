package com.example.caigouapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;
import com.example.caigouapp.ui.shopping.ShoppingAdapter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private Map<Integer, Boolean> map = new TreeMap<>();
    private int totalSize = 0;

    public int getTotalSize(){
        return totalSize;
    }

    public ArrayList<Ingredient> getSendList() {
        return sendList;
    }

    public ArrayList<Ingredient> sendList = new ArrayList<>();

    public IngredientAdapter(ArrayList<Ingredient> list,int totalSize){
        this.totalSize = totalSize;
        ingredientList.clear();
        ingredientList.addAll(list);
        sendList.addAll(list);
        for(int i = 0;i<ingredientList.size();i++){
            map.put(i,true);
        }
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientWeight.setText(ingredient.getWeight());
        holder.ingredientCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    map.put(position, true);
                    sendList.add(ingredientList.get(position));
                } else {
                    map.remove(position);
                    sendList.remove(ingredientList.get(position));
                }
            }
        });
        holder.ingredientCheck.setChecked(map != null && map.containsKey(position));
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientName;
        TextView ingredientWeight;
        CheckBox ingredientCheck;

        public ViewHolder(View itemView){
            super(itemView);
            ingredientName = (TextView)itemView.findViewById(R.id.ingredientName);
            ingredientWeight = (TextView)itemView.findViewById(R.id.ingredientWeight);
            ingredientCheck = (CheckBox)itemView.findViewById(R.id.ingredientCheck);
        }
    }
}
