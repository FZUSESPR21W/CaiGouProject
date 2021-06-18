package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
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
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private RecipeSearchAdapter adapter;
    private List<MenusBean> recipeList = new ArrayList<>();
    List<String> historyList = new ArrayList<>();
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        
        content = getIntent().getStringExtra("content");
        if (content != null && !content.equals("")){
            HashMap<String, String> map = new HashMap<>();
            map.put("searchWord",content);
            binding.pbLoad.setVisibility(View.VISIBLE);
            initData(new Gson().toJson(map));
        }
        initHistory();
        initView();
        setContentView(binding.getRoot());
    }

    private void initHistory() {
        String historyStr = SpUtil.getInstance().getString("history","");
        if (!historyStr.equals("")){
            historyList.addAll(Arrays.asList(historyStr.split(",")));
        }
    }

    private void initData(String searchContent) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), searchContent);
        //网络请求
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeServices recipeServices = retrofit.create(RecipeServices.class);
        Call<SearchResponse> call = recipeServices.searchRecipes(requestBody);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()){
                    runOnUiThread(() -> {
                        recipeList.clear();
                        recipeList.addAll(response.body().getMenus());
                        adapter.notifyDataSetChanged();
                        binding.tvHistoryHead.setVisibility(View.GONE);
                        binding.tvClearHistory.setVisibility(View.GONE);
                        binding.rvSearchHistory.setVisibility(View.GONE);
                        binding.pbLoad.setVisibility(View.GONE);
                        if (recipeList.size() == 0){
                            binding.rvRecipeList.setVisibility(View.GONE);
                            binding.tvBlank.setVisibility(View.VISIBLE);
                        }
                        else{
                            binding.tvBlank.setVisibility(View.GONE);
                            binding.rvRecipeList.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("SearchActivity",t.getMessage()+call.toString());
            }
        });
    }

    private void initView(){
        TextAdapter textAdapter = new TextAdapter(historyList, this);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        binding.rvSearchHistory.setLayoutManager(layoutManager);
        // 历史记录
        binding.rvSearchHistory.setAdapter(textAdapter);
        if (content != null && content.contains("菜")){
            binding.rvSearchHistory.setVisibility(View.GONE);
            binding.tvHistoryHead.setVisibility(View.GONE);
            binding.tvClearHistory.setVisibility(View.GONE);
        }

        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch();
                return true;
            }
            return false;
        });

        binding.tvClearHistory.setOnClickListener(v -> {
            SpUtil.getInstance().putString("history","");
            initHistory();
            historyList.clear();
            textAdapter.notifyDataSetChanged();
        });

        if (recipeList.size() != 0){
            binding.rvSearchHistory.setVisibility(View.GONE);
            binding.tvHistoryHead.setVisibility(View.GONE);
            binding.tvBlank.setVisibility(View.GONE);
        }

        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvRecipeList.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        adapter = new RecipeSearchAdapter(recipeList, this);
        binding.rvRecipeList.setAdapter(adapter);

        binding.tvSearch.setOnClickListener(v -> {
            doSearch();
        });
    }

    private void doSearch(){
        String content = binding.etSearch.getText().toString();
        if (content != null && !content.equals("")){
            String str = SpUtil.getInstance().getString("history","");
            if (!str.contains(content)){
                if (str.equals(""))
                    SpUtil.getInstance().putString("history",content);
                else
                    SpUtil.getInstance().putString("history",str+","+content);
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("searchWord",content);
            binding.pbLoad.setVisibility(View.VISIBLE);
            initData(new Gson().toJson(map));
        }
    }
}