package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.data.AddressBean;
import com.example.caigouapp.data.UserAddressResponse.*;
import com.example.caigouapp.ui.shopping.ShoppingAdapter;

import java.util.HashMap;
import java.util.List;

public class MineAddressAdapter extends RecyclerView.Adapter<MineAddressAdapter.ViewHolder> {
    private List<AddressBean> addressList;
    private Context context;
    private int index = -1;
    private boolean start = true;
    public MineAddressAdapter(List<AddressBean> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
    }

    public int getSelectAddress(){
        if (index >= 0)
        return addressList.get(index).getId();
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_lv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddressBean data = addressList.get(position);
        if(data.getStatus() == 1 && start){
            start = false;
            holder.rb.setChecked(true);
            index = position;
        }
        holder.address.setText(data.getAddress());
        holder.name.setText(data.getName());
        holder.phone.setText(data.getPhone());
        holder.rb.setOnClickListener(view -> {
            holder.rb.setChecked(true);
            index = position;
            notifyDataSetChanged();
        });
        holder.itemView.setOnClickListener(view -> {
            holder.rb.setChecked(true);
            index = position;
            notifyDataSetChanged();
        });
        holder.rb.setChecked(index == position);
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RadioButton rb;
        TextView address;
        TextView name;
        TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb = itemView.findViewById(R.id.choose);
            address = itemView.findViewById(R.id.mine_address);
            name = itemView.findViewById(R.id.tv_name);
            phone = itemView.findViewById(R.id.tv_phone);
        }
    }
}
