package com.example.caigouapp.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.databinding.FragmentShoppingBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private FragmentShoppingBinding binding;
    private List<RecipeBean> list = new ArrayList<>();
    private List<Ingredient> ingredient = new ArrayList<>();
    private List<Ingredient> side_ingredient = new ArrayList<>();
    private List<String> step = new ArrayList<>();
    private ShoppingAdapter adapter;
    private double totalPrice = 0;
    DecimalFormat df = new DecimalFormat( "0.00");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShoppingBinding.inflate(getLayoutInflater());
        initView();
        return binding.getRoot();
    }

    private void initData(){
        ingredient.add(new Ingredient("西红柿","两个"));
        ingredient.add(new Ingredient("鸡蛋","一个"));
        side_ingredient.add(new Ingredient("小葱","一把"));
        side_ingredient.add(new Ingredient("盐","少许"));
        side_ingredient.add(new Ingredient("糖","少许"));
        side_ingredient.add(new Ingredient("胡椒","少许"));
        side_ingredient.add(new Ingredient("清水","少许"));
        step.add("1.放入番茄");
        step.add("2.放入鸡蛋");
        for(int i = 0;i<10;i++)
            list.add(new RecipeBean("测试菜谱"+i,"测试介绍",6.66,ingredient,side_ingredient,step));
    }

    private void countPrice(){
        totalPrice = 0;
        for(int i = 0 ; i < list.size();i++){
            totalPrice+=list.get(i).getPrice();
        }

    }

    private void initView(){
        initData();
        countPrice();
        String str = "共有"+list.size()+"件菜品";
        String price = "￥"+df.format(totalPrice);
        binding.shoppingRecipeTotal.setText(str);
        binding.shoppingCarTotal.setText(price);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.shoppingCarRv.setLayoutManager(layoutManager);
        adapter = new ShoppingAdapter(list,getContext());
        binding.shoppingCarRv.setAdapter(adapter);
        binding.manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.manageButton.getText().equals("管理")){
                    adapter.open = true;
                    adapter.notifyDataSetChanged();
                    binding.manageButton.setText("删除");
                    binding.total.setVisibility(View.GONE);
                    binding.submitButton.setVisibility(View.GONE);
                    binding.shoppingCarTotal.setVisibility(View.GONE);
                }
                else if (binding.manageButton.getText().equals("删除")){
                    int t = list.size();
                    adapter.delete();
                    delete();
                    countPrice();
                    String price = "￥"+df.format(totalPrice);
                    String str = "共有"+list.size()+"件菜品";
                    adapter.open = false;
                    adapter.notifyDataSetChanged();
                    binding.shoppingCarTotal.setText(price);
                    binding.shoppingRecipeTotal.setText(str);
                    binding.manageButton.setText("管理");
                    binding.total.setVisibility(View.VISIBLE);
                    binding.submitButton.setVisibility(View.VISIBLE);
                    binding.shoppingCarTotal.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"已经删除了"+(t-list.size())+"个商品",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void delete(){
        int i = 0;
        for (int position:adapter.getMap().keySet()) {
            list.remove(position-i);
            i++;
        }
    }
}