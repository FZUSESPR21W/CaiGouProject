package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;
import com.example.caigouapp.data.TagsBean;
import com.example.caigouapp.data.UserTagResponse.*;

import java.util.List;

public class MineTagChooseAdapter extends RecyclerView.Adapter<MineTagChooseAdapter.MineViewHolder> {
    private List<TagsBean> data;
    private Context context;

    public MineTagChooseAdapter(List<TagsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MineTagChooseAdapter.MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mine_recyclerview_item, null);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTagChooseAdapter.MineViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getTag());
        if(data.get(position).getStatus() == 0){
            holder.tv.setBackgroundResource(R.drawable.bg_round);
            holder.tv.setTextColor(0XFF000000);
        }
        else if(data.get(position).getStatus() == 1){
            holder.tv.setBackgroundResource(R.drawable.bg_tag);
            holder.tv.setTextColor(0XFFffffff);
        }
        holder.tv.setOnClickListener(v -> {
            if (data.get(position).getStatus() == 0)
            {
                data.get(position).setStatus(1);
            }
            else
            {
                data.get(position).setStatus(0);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MineViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MineViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.mine_tag_tv);
        }
    }
}
