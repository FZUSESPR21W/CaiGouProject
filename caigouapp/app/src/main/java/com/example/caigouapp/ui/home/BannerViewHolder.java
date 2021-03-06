package com.example.caigouapp.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.caigouapp.MyApplication;
import com.example.caigouapp.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public class BannerViewHolder implements MZViewHolder<String> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = view.findViewById(R.id.banner_image);
        mImageView.setImageResource(R.drawable.default_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        Log.d("banner",data+"*");
        if (data == null || data.equals(""))
            mImageView.setImageResource(R.drawable.default_image);
        else
            Glide.with(MyApplication.getContext()).load(data).error(R.drawable.default_image).into(mImageView);
    }

//    @Override
//    public void onBind(Context context, int position, Integer data) {
//        // 数据绑定
//        mImageView.setImageResource(data);
//    }
}