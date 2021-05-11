package com.example.caigouapp.ui.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.caigouapp.R;

import java.util.ArrayList;
import java.util.List;

public class TagChooseActivity extends AppCompatActivity {

    private List<MineBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_choose);
        initView();
    }

    private void initView(){
        ImageView imageView = (ImageView)findViewById(R.id.btn_arrow_left);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView imageView1 = (ImageView) findViewById(R.id.btn_selected);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for (int i = 0; i < 1000; i++) {
            MineBean mineBean = new MineBean();
            mineBean.setName("标签" + i);
            data.add(mineBean);
        }

        RadioButton radioButton = findViewById(R.id.rb_tag_choose);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cb_tag_choose);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        MineTagChooseAdapter mineAdapter = new MineTagChooseAdapter(data, this);
        Log.d("567","```"+data.size());
        recyclerView.setAdapter(mineAdapter);
    }
}