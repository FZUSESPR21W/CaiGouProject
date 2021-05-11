package com.example.caigouapp.ui.order;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    List<Order> list;
    Order mOrder;
    ArrayList<Icon> mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        list = OrderFragment.getOrderList();
        mOrder = list.get(2);
        mIcon = mOrder.getIcons();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_order_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerView.setAdapter(new OrderDetailAdapter(mIcon));
    }
}