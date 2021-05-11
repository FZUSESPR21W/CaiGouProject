package com.example.caigouapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;

import java.util.ArrayList;

public class SideIngredientGridAdapter extends BaseAdapter {
    private ArrayList<Ingredient> sideIngredientList = new ArrayList<>();

    public  SideIngredientGridAdapter(ArrayList<Ingredient> list){
        sideIngredientList.clear();
        sideIngredientList.addAll(list);
    }

    @Override
    public int getCount() {
        return sideIngredientList.size();
    }

    @Override
    public Object getItem(int i) {
        return sideIngredientList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_side_ingredient,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
            viewHolder.tvName.setText(sideIngredientList.get(position).getName());
            viewHolder.tvWeight.setText(sideIngredientList.get(position).getPortion());
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    class ViewHolder {
        private TextView tvName;
        private TextView tvWeight;
        public ViewHolder(View view){
            tvName = (TextView)view.findViewById(R.id.sideIngredientName);
            tvWeight = (TextView)view.findViewById(R.id.sideIngredientWeight);
        }
    }
}
