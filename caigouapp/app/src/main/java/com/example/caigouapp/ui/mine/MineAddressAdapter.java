package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.caigouapp.R;
import com.example.caigouapp.data.UserAddressResponse.*;

import java.util.HashMap;
import java.util.List;

public class MineAddressAdapter extends BaseAdapter {
    private List<AddressBean> addressList;
    private Context context;
    HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    public MineAddressAdapter(List<AddressBean> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
        if (!addressList.isEmpty())
            states.put(String.valueOf(0), true);
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = View.inflate(context, R.layout.item_address_lv, null);
        }else {
            view = convertView;
        }
        RadioButton address = view.findViewById(R.id.mine_address);
        TextView phone = view.findViewById(R.id.tv_phone);
        address.setText(addressList.get(position).getAddress());
        phone.setText(addressList.get(position).getPhone());
        address.setOnClickListener(v -> {
            for(String key : states.keySet()) {
                states.put(key, false);
            }
            states.put(String.valueOf(position), true);
            MineAddressAdapter.this.notifyDataSetChanged();
        });
        boolean res = false;
        if(states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
            res = false;
            states.put(String.valueOf(position), false);
        } else
            res = true;

        address.setChecked(res);
        return view;
    }
}
