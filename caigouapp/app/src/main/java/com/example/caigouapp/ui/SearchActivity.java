package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.caigouapp.data.RecipeBean;
import com.example.caigouapp.databinding.ActivitySearchBinding;
import com.example.caigouapp.ui.adapter.RecipeSearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private RecipeSearchAdapter adapter;
    private List<RecipeBean> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        initData();
        initView();
        setContentView(binding.getRoot());
    }

    private void initData() {
        recipeList.add(new RecipeBean("无油可乐鸡翅", "步骤", 1, "所需材料","快手菜", "https://st-cn.meishij.net/r/150/151/14787900/s14787900_161545896864154.jpg",16));
        recipeList.add(new RecipeBean("蒸鲈鱼", "步骤", 1, "所需材料" ,"快手菜", "https://st-cn.meishij.net/r/208/102/1025708/s1025708_156345334153163.jpg",10));
        recipeList.add(new RecipeBean("西芹山药炒木耳", "步骤", 1,"所需材料", "快手菜", "https://st-cn.meishij.net/r/06/177/6419256/s6419256_157777650120131.jpg",10));
    }

    private void initView(){
        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvRecipeList.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        adapter = new RecipeSearchAdapter(recipeList, this);
        binding.rvRecipeList.setAdapter(adapter);
    }
}