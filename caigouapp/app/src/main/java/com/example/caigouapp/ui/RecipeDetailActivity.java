package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.databinding.ActivityRecipeDetailBinding;
import com.example.caigouapp.ui.adapter.IngredientAdapter;
import com.example.caigouapp.ui.adapter.RecipeStepAdapter;
import com.example.caigouapp.ui.adapter.SideIngredientGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    private ActivityRecipeDetailBinding binding;
    private RecipeBean data;
    private ArrayList<Ingredient> ingredient = new ArrayList<>();
    private ArrayList<Ingredient> sideIngredient = new ArrayList<>();
    private ArrayList<String> step = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        data = (RecipeBean) getIntent().getSerializableExtra("recipe");
        initView();
        setContentView(binding.getRoot());
    }

    private void initView(){
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        binding.recipeName.setText(data.getName());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        binding.ingredientRv.setLayoutManager(layoutManager1);
        IngredientAdapter ingredientAdapter = new IngredientAdapter((ArrayList<Ingredient>) data.getIngredient());
        binding.ingredientRv.setAdapter(ingredientAdapter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        binding.stepRv.setLayoutManager(layoutManager2);
        RecipeStepAdapter stepAdapter = new RecipeStepAdapter((ArrayList<String>) data.getStep());
        binding.stepRv.setAdapter(stepAdapter);
        binding.sideIngredientGv.setAdapter(new SideIngredientGridAdapter((ArrayList<Ingredient>) data.getSide_ingredient()));
        binding.addButton.setOnClickListener(view -> {
            if(ingredientAdapter.getSendList().size() == 0){
                Toast.makeText(this,"您还没有选择要购买的商品！",Toast.LENGTH_SHORT).show();
            }
            else {
                RecipeDialog dialog = new RecipeDialog(ingredientAdapter.getSendList(), data);
                dialog.show(getSupportFragmentManager(), "tag");
            }
        });
        binding.toCarButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("shoppingCar",1);
            startActivity(intent);
        });
    }

    private void initData(){
        ingredient.add(new Ingredient("西红柿","两个"));
        ingredient.add(new Ingredient("鸡蛋","一个"));
        sideIngredient.add(new Ingredient("小葱","一把"));
        sideIngredient.add(new Ingredient("盐","少许"));
        sideIngredient.add(new Ingredient("糖","少许"));
        sideIngredient.add(new Ingredient("胡椒","少许"));
        sideIngredient.add(new Ingredient("七彩葫芦藤的种子","少许"));
        step.add("1.放入番茄");
        step.add("2.放入鸡蛋");
    }
}