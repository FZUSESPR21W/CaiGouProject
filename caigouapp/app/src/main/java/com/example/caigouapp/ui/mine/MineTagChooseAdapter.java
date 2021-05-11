package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;

import java.util.List;

public class MineTagChooseAdapter extends RecyclerView.Adapter<MineTagChooseAdapter.MineViewHolder> {
    private List<MineBean> data;
    private Context context;

    public MineTagChooseAdapter(List<MineBean> data, Context context) {
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
        holder.tv.setText(data.get(position).getName());
//        判断已选标签
//        holder.tv.setChecked(true);
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
