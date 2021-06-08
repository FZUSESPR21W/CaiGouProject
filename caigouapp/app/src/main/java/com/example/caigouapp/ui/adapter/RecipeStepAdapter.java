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
import com.example.caigouapp.Step;

import java.util.ArrayList;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.ViewHolder> {

    private ArrayList<Step> stepList = new ArrayList<>();
    private Context mContext;

    public RecipeStepAdapter(ArrayList<Step> list, Context context){
        mContext = context;
        stepList.clear();
        stepList.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_step,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String step = "步骤"+ (position + 1);
        holder.stepNum.setText(step);
        Glide.with(mContext).load(stepList.get(position).getImgUrl()).error(R.drawable.hui).into(holder.stepImage);
        holder.stepContent.setText(stepList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView stepNum;
        ImageView stepImage;
        TextView stepContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNum = itemView.findViewById(R.id.stepId);
            stepImage = itemView.findViewById(R.id.stepImage);
            stepContent = itemView.findViewById(R.id.stepContent);
        }
    }
}
