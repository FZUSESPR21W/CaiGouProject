package com.unoni.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        list = CourseFragment.getOrderList();
        mOrder = list.get(2);
        mIcon = mOrder.getIcons();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_order_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerView.setAdapter(new OrderDetailAdapter(mIcon));
    }
}