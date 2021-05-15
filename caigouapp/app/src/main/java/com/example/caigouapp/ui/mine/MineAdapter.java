package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;
import com.example.caigouapp.data.UserTagResponse.*;

import java.util.List;

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineViewHolder> {

    private List<TagsBean> data;
    private Context context;

    public MineAdapter(List<TagsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MineAdapter.MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mine_recyclerview_item, null);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineAdapter.MineViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getTag());
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
