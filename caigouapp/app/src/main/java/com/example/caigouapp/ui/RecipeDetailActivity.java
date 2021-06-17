package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.caigouapp.Ingredient;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.Step;
import com.example.caigouapp.data.RecipeDetailResponse;
import com.example.caigouapp.databinding.ActivityRecipeDetailBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.ui.adapter.IngredientAdapter;
import com.example.caigouapp.ui.adapter.RecipeStepAdapter;
import com.example.caigouapp.ui.adapter.SideIngredientGridAdapter;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeDetailActivity extends AppCompatActivity {

    private ActivityRecipeDetailBinding binding;
    private RecipeBean data;
    private ArrayList<Ingredient> ingredient = new ArrayList<>();
    private ArrayList<Ingredient> sideIngredient = new ArrayList<>();
    private ArrayList<Step> step = new ArrayList<>();
    private String token = SpUtil.getInstance().getString("token",null);
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        id = getIntent().getIntExtra("id",-1);
        internetRequest(id);
        setContentView(binding.getRoot());
    }

    private void internetRequest(int id){
        binding.ingredientRv.setVisibility(View.GONE);
        binding.sideIngredientGv.setVisibility(View.GONE);
        binding.stepRv.setVisibility(View.GONE);
        binding.loading1.setVisibility(View.VISIBLE);
        binding.loading2.setVisibility(View.VISIBLE);
        binding.loading3.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String , Integer> map = new HashMap<>();
        map.put("id",id);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        RecipeServices recipeServices = retrofit.create(RecipeServices.class);
        Call<RecipeDetailResponse> call = recipeServices.getRecipeDetail(token,requestBody);
        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if (response.body() != null && response.body().getCode() == 200){
                    RecipeDetailResponse.DataBean dataBean = response.body().getData();
                    //解析ingredient
                    String[] weight = dataBean.getFood_weight_list().split(",");
                    for(int i = 0;i<weight.length;i++){
                        int major = dataBean.getFood().get(i).getMajor();
                        Ingredient in = new Ingredient(
                                dataBean.getFood().get(i).getIngredient(),
                                weight[i],
                                dataBean.getFood().get(i).getId(),
                                dataBean.getFood().get(i).getPrice());
                        in.setIndex(i);
                        if(major == 1){
                            ingredient.add(new Ingredient(in));
                        }
                        else if(major == 0){
                            sideIngredient.add(new Ingredient(in));
                        }
                    }
                    //解析step
                    String[] steps = dataBean.getMethod().split("\n");
                    Pattern httpPattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                    String url = null;
                    String content = null;
                    for (String s : steps) {
                        Matcher isHttp = httpPattern.matcher(s);
                        if (!s.contains("步骤")) {
                            if (isHttp.matches()) {
                                url = s;
                            } else {
                                content = s;
                            }
                            if(content != null){
                                step.add(new Step(url,content));
                                url = null;
                                content = null;
                            }
                        }
                    }
                    //解析recipe
                    data = new RecipeBean(dataBean.getId(),dataBean.getName(),dataBean.getTags(),0,dataBean.getAvatar(),ingredient,sideIngredient,step);
                    Log.d("tag",data.getId()+"");
                }
                runOnUiThread(() -> initView());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                runOnUiThread(() -> Toast.makeText(RecipeDetailActivity.this, "好像出了点问题……", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void initView(){
        binding.ingredientRv.setVisibility(View.VISIBLE);
        binding.sideIngredientGv.setVisibility(View.VISIBLE);
        binding.stepRv.setVisibility(View.VISIBLE);
        binding.loading1.setVisibility(View.GONE);
        binding.loading2.setVisibility(View.GONE);
        binding.loading3.setVisibility(View.GONE);
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        binding.recipeName.setText(data.getName());
        Glide.with(this).load(data.getImageUrl()).error(R.drawable.hui).into(binding.recipeImage);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        binding.ingredientRv.setLayoutManager(layoutManager1);
        IngredientAdapter ingredientAdapter = new IngredientAdapter((ArrayList<Ingredient>) data.getIngredient(),data.getIngredient().size()+data.getSide_ingredient().size());
        binding.ingredientRv.setAdapter(ingredientAdapter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        binding.stepRv.setLayoutManager(layoutManager2);
        RecipeStepAdapter stepAdapter = new RecipeStepAdapter((ArrayList<Step>) data.getStep(),this);
        binding.stepRv.setAdapter(stepAdapter);
        binding.sideIngredientGv.setAdapter(new SideIngredientGridAdapter((ArrayList<Ingredient>) data.getSide_ingredient()));
        binding.addButton.setOnClickListener(view -> {
            if(ingredientAdapter.getSendList().size() == 0){
                Toast.makeText(this,"您还没有选择要购买的商品！",Toast.LENGTH_SHORT).show();
            }
            else {
                RecipeDialog dialog = new RecipeDialog(ingredientAdapter.getSendList(), data ,ingredientAdapter.getTotalSize());
                dialog.show(getSupportFragmentManager(), "tag");
            }
        });
        binding.toCarButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("shoppingCar",1);
            startActivity(intent);
        });
    }

}