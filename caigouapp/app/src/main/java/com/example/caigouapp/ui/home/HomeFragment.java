package com.example.caigouapp.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.caigouapp.R;
import com.example.caigouapp.data.RecommendResponse;
import com.example.caigouapp.data.UserAddressResponse;
import com.example.caigouapp.data.UserTagResponse;
import com.example.caigouapp.databinding.FragmentHomeBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.ui.RecipeDetailActivity;
import com.example.caigouapp.ui.SearchActivity;
import com.example.caigouapp.utils.SpUtil;
import com.example.caigouapp.utils.StatusBarUtils;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Call<RecommendResponse> call;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        initData();
        initView();
        initStatusBar();//初始化状态栏
        return binding.getRoot();
    }

    //今日推荐
    private void initData() {
        Integer id = SpUtil.getInstance().getInt("id",0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeServices recipeServices = retrofit.create(RecipeServices.class);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("id",id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        call = recipeServices.getCommendRecipe(body);
        call.enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(() -> {
                        binding.tvCommend.setText("今日推荐-"+response.body().getData().getName());
                        Glide.with(getActivity()).load(response.body().getData().getAvatar()).into(binding.ivRecommend);
                        binding.ivRecommend.setOnClickListener(v -> {
                            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
                            intent.putExtra("id",response.body().getData().getId());
                            startActivity(intent);
                        });
                    });
                }
            }
            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                Log.d("HomeFragment","今日推荐加载失败");
            }
        });
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(getActivity(), R.color.white);
        }
    }

    public void initView(){
        //初始化轮播图
        List pictureList = new ArrayList<>(Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3));
        binding.mzbHome.setPages(pictureList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        //初始化菜系
        ArrayList styleList = new ArrayList<>(Arrays.asList(
                R.drawable.min,
                R.drawable.hui,
                R.drawable.lu,
                R.drawable.yue,
                R.drawable.chuan,
                R.drawable.zhe,
                R.drawable.xiang,
                R.drawable.su));
        binding.gvCaixi.setAdapter(new GridViewAdapter(styleList,getContext()));
        binding.clTop.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call.isExecuted())
            call.cancel();
    }
}