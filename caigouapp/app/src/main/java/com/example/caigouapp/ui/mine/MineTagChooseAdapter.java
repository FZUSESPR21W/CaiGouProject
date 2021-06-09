package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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
        View view = View.inflate(context, R.layout.item_tag_choose, null);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTagChooseAdapter.MineViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getTag());
        if (data.get(position).getStatus() == 1)
            holder.tv.setChecked(true);
        else
            holder.tv.setChecked(false);
        holder.tv.setOnClickListener(v -> {
            if (data.get(position).getStatus() == 0)
                data.get(position).setStatus(1);
            else
                data.get(position).setStatus(0);
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MineViewHolder extends RecyclerView.ViewHolder {
        private CheckBox tv;

        public MineViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.cb_tag_choose);
        }
    }
}
