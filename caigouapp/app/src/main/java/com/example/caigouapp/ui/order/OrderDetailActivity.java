package com.example.caigouapp.ui.order;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;
import com.example.caigouapp.utils.GraphicUtil;
import com.example.caigouapp.utils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    List<Order> list;
    Order mOrder;
    ArrayList<CustomerMenu> mCms;
    RecyclerView recyclerView;
    TextView stateTextView;
    TextView numberTextView;
    TextView copyButton;
    TextView createTextView;
    TextView serveTextView;
    TextView addressTextView;
    TextView phoneTextView;
    TextView remarkTextView;
    String data;//传到该活动的订单的json字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        data = getIntent().getStringExtra("data");
        mOrder  = GsonUtil.getOrder(data);

        //list = GsonUtil.getDataList(OrderDetailActivity.this);
        mCms = mOrder.getCms();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_order_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerView.setAdapter(new OrderDetailAdapter(OrderDetailActivity.this,mCms));

        //复制按钮!!!!!!
        stateTextView = (TextView) findViewById(R.id.txt_order_detail_state);
        numberTextView = (TextView) findViewById(R.id.txt_order_detail_number);
        copyButton = (TextView) findViewById(R.id.btn_order_detail_copy_number);
        createTextView = (TextView) findViewById(R.id.txt_order_detail_create_time);
        serveTextView = (TextView) findViewById(R.id.txt_order_detail_serve_time);
        addressTextView = (TextView) findViewById(R.id.txt_order_detail_address);
        phoneTextView = (TextView) findViewById(R.id.txt_order_detail_phone);
        remarkTextView = (TextView) findViewById(R.id.txt_order_detail_remark);
        ImageView back = findViewById(R.id.btn_arrow_left);

        stateTextView.setText(mOrder.getOrderStateString());
        numberTextView.setText(mOrder.getOrderNumber());
        createTextView.setText(mOrder.getOrderCreateTime());
        serveTextView.setText(mOrder.getOrderServeTime());
        addressTextView.setText(mOrder.getAddress());
        phoneTextView.setText(mOrder.getPhone());
        remarkTextView.setText(mOrder.getRemark());

        back.setOnClickListener(v -> {
            finish();
        });

        copyButton.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", mOrder.getOrderNumber());
            cm.setPrimaryClip(mClipData);
            Toast.makeText(OrderDetailActivity.this,"已复制到剪贴板",Toast.LENGTH_SHORT).show();
        });
    }
}