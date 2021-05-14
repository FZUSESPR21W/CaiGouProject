package com.example.caigouapp.ui.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.caigouapp.R;

import java.util.HashMap;
import java.util.List;

public class MineAddressAdapter extends BaseAdapter {
    private List<String> listText;
    private Context context;
    HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    public MineAddressAdapter(List<String> listText, Context context) {
        this.listText = listText;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listText.size();
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
        TextView radioText = (TextView)view.findViewById(R.id.address_tv);
        RadioButton radioButton = (RadioButton)view.findViewById(R.id.mine_phone_num);
        radioText.setText(listText.get(position));
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String key : states.keySet()) {
                    states.put(key, false);
                }
                states.put(String.valueOf(position), true);
                MineAddressAdapter.this.notifyDataSetChanged();
            }
        });
        boolean res = false;
        if(states.get(String.valueOf(position)) == null || states.get(String.valueOf(position)) == false) {
            res = false;
            states.put(String.valueOf(position), false);
        } else
            res = true;

        radioButton.setChecked(res);
        return view;
    }
}
