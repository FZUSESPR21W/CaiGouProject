package com.example.caigouapp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;

import java.util.ArrayList;

public class UploadIngredientAdapter extends RecyclerView.Adapter<UploadIngredientAdapter.ViewHolder> {
    private ArrayList<Ingredient> uploadList = new ArrayList<>();

    public ArrayList<Ingredient> getUploadList() {
        return uploadList;
    }

    public UploadIngredientAdapter(ArrayList<Ingredient> list){
        uploadList.clear();
        uploadList.addAll(list);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_portion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredientName.setText(uploadList.get(position).getName());
        holder.ingredientWeight.setText(uploadList.get(position).getWeight());
        holder.ingredientPortion.setText(String.valueOf(uploadList.get(position).getPortion()));
        holder.subButton.setOnClickListener(view -> {
            int i = uploadList.get(position).getPortion();
            if(i > 1){
                i--;
                uploadList.get(position).setPortion(i);
                holder.ingredientPortion.setText(String.valueOf(uploadList.get(position).getPortion()));
            }
        });
        holder.addButton.setOnClickListener(view -> {
            int i = uploadList.get(position).getPortion();
            if(i < 100){
                i++;
                uploadList.get(position).setPortion(i);
                holder.ingredientPortion.setText(String.valueOf(uploadList.get(position).getPortion()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientName;
        TextView ingredientWeight;
        TextView ingredientPortion;
        Button subButton;
        Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = (TextView)itemView.findViewById(R.id.uploadIngredientName);
            ingredientWeight = (TextView)itemView.findViewById(R.id.uploadIngredientWeight);
            ingredientPortion = (TextView)itemView.findViewById(R.id.uploadIngredientPortion);
            subButton = (Button)itemView.findViewById(R.id.subButton);
            addButton = (Button)itemView.findViewById(R.id.addButton);
        }
    }
}
