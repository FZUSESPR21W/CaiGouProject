package com.example.caigouapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PersonalIngredientAdapter extends RecyclerView.Adapter<PersonalIngredientAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredientList = new ArrayList<>();

    public PersonalIngredientAdapter(ArrayList<Ingredient> list){
        ingredientList.clear();
        ingredientList.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_ingredient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.ingredientName.setText(ingredient.getName());
        holder.ingredientWeight.setText(ingredient.getWeight());
        String portion = "× " + ingredient.getPortion();
        holder.ingredientPortion.setText(portion);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientName;
        TextView ingredientWeight;
        TextView ingredientPortion;

        public ViewHolder(View itemView){
            super(itemView);
            ingredientName = (TextView)itemView.findViewById(R.id.personalIngredientName);
            ingredientWeight = (TextView)itemView.findViewById(R.id.personalIngredientWeight);
            ingredientPortion = (TextView)itemView.findViewById(R.id.personalIngredientPortion);
        }
    }
}
