package com.example.caigouapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.caigouapp.R;

import java.util.ArrayList;

class GridViewAdapter extends BaseAdapter {

    private ArrayList<Integer> picList;

    public GridViewAdapter(ArrayList<Integer> picList) {
        this.picList = picList;
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
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    class ViewHolder {
        ImageView itemImg;
    }
}