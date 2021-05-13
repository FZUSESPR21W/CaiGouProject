package com.example.caigouapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.caigouapp.R;
import com.example.caigouapp.ui.SearchActivity;

import java.util.ArrayList;

class GridViewAdapter extends BaseAdapter {

    private ArrayList<Integer> picList;
    private String[] nameArr = {"闽菜","徽菜","鲁菜","粤菜","川菜","浙菜","湘菜","苏菜"};
    private Context context;

    public GridViewAdapter(ArrayList<Integer> picList, Context context) {
        this.picList = picList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caixi_grid_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.iv_item);
            convertView.setTag(viewHolder);
            viewHolder.itemImg.setImageResource(picList.get(position));
            viewHolder.itemImg.setOnClickListener(v -> {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("content",nameArr[position]);
                context.startActivity(intent);
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    class ViewHolder {
        ImageView itemImg;
    }
}