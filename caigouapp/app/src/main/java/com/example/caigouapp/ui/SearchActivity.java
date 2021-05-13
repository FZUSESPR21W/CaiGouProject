package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.caigouapp.SignupActivity;
import com.example.caigouapp.data.SearchResponse;
import com.example.caigouapp.data.SearchResponse.MenusBean;
import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivitySearchBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.ui.adapter.RecipeSearchAdapter;
import com.example.caigouapp.ui.adapter.TextAdapter;
import com.example.caigouapp.utils.SpUtil;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private RecipeSearchAdapter adapter;
    private List<MenusBean> recipeList = new ArrayList<>();
    private String[] historyArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        String content = getIntent().getStringExtra("content");
        initData(content);
        initView();
        setContentView(binding.getRoot());
    }

    private void initData(String searchContent) {
        String historyStr = SpUtil.getInstance().getString("history","");
        if (!historyStr.equals("")){
            historyArr = historyStr.split(",");
        }
        //网络请求
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .build();
        RecipeServices recipeServices = retrofit.create(RecipeServices.class);
        Call<SearchResponse> call = recipeServices.searchRecipes(searchContent);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body().getCode() == 200){
                    recipeList.addAll(response.body().getMenus());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("SearchActivity","error");
            }
        });
    }

    private void initView(){

        if (historyArr != null && historyArr.length != 0){
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
            layoutManager.setFlexDirection(FlexDirection.ROW);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
            binding.rvSearchHistory.setLayoutManager(layoutManager);
            // 历史记录
            TextAdapter textAdapter = new TextAdapter(historyArr, this);
            binding.rvSearchHistory.setAdapter(textAdapter);
        }

        if (recipeList.size() != 0){
            binding.rvSearchHistory.setVisibility(View.GONE);
            binding.tvHistoryHead.setVisibility(View.GONE);
        }

        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvRecipeList.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        adapter = new RecipeSearchAdapter(recipeList, this);
        binding.rvRecipeList.setAdapter(adapter);

        binding.tvSearch.setOnClickListener(v -> {
            String content = binding.etSearch.getText().toString();
            if (content != null && !content.equals("")){
                String str = SpUtil.getInstance().getString("history","");
                SpUtil.getInstance().putString("history",str+","+content);
                initData(content);
            }
        });
    }
}