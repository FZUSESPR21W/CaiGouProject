package com.example.caigouapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;
import com.example.caigouapp.ui.SearchActivity;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private String[] arr;
    private Context mContext;

    public TextAdapter(String[] list, Context context){
        this.arr = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_layout,parent,false);
        return new TextAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.ViewHolder holder, int position) {
        String content = arr[position];
        holder.historyContent.setText(content);
        holder.historyContent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, SearchActivity.class);
            intent.putExtra("content",content);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView historyContent;

        public ViewHolder(View itemView){
            super(itemView);
            historyContent = (TextView)itemView.findViewById(R.id.tv_history_content);
        }
    }
}