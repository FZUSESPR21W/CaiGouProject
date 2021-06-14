package com.example.caigouapp.ui.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.caigouapp.Ingredient;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.MyApplication;
import com.example.caigouapp.OrderDialog;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.Step;
import com.example.caigouapp.data.CartResponse;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.RecipeDetailResponse;
import com.example.caigouapp.data.RecommendResponse;
import com.example.caigouapp.databinding.FragmentShoppingBinding;
import com.example.caigouapp.http.CartServices;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.ui.RecipeDetailActivity;
import com.example.caigouapp.ui.RecipeDialog;
import com.example.caigouapp.utils.SpUtil;
import com.example.caigouapp.utils.StatusBarUtils;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingFragment extends Fragment {

    private FragmentShoppingBinding binding;
    private List<RecipeBean> list = new ArrayList<>();
    private List<Ingredient> ingredient = new ArrayList<>();
    private List<Ingredient> sideIngredient = new ArrayList<>();
    private List<Step> step = new ArrayList<>();
    private ShoppingAdapter adapter;
    private double totalPrice = 0;
    private String token = SpUtil.getInstance().getString("token",null);
    private int userId = SpUtil.getInstance().getInt("id",0);
    private String remark = "";
    DecimalFormat df = new DecimalFormat( "0.00");
    Call<CartResponse> call;
    Call<RecommendResponse> recommendCall;
    Call<CommonResponse> postCall;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShoppingBinding.inflate(getLayoutInflater());
        binding.loading.setVisibility(View.VISIBLE);
        binding.noItem.setVisibility(View.GONE);
        binding.recommend.setVisibility(View.GONE);
        binding.recommendTitle.setVisibility(View.GONE);
        getCartRequest(userId);
        getRecommendRequest();
        initStatusBar();//初始化状态栏
        return binding.getRoot();
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(getActivity(), R.color.white);
        }
    }

    private void postRequest(int userId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String , Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("remark",remark);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        CartServices cartServices = retrofit.create(CartServices.class);
        postCall = cartServices.postOrder(token,requestBody);
        postCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.body().getCode().equals("200")) {
                    Toast.makeText(getContext(), "已经为您下单啦", Toast.LENGTH_SHORT).show();
                    list.clear();
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    binding.noItem.setVisibility(View.VISIBLE);
                    initView();
                }
                else if(response.body().getCode().equals("100")){
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getContext(),"好像出了一点问题……",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRecommendRequest() {
        Integer id = SpUtil.getInstance().getInt("id",0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeServices recipeServices = retrofit.create(RecipeServices.class);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("id",id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        recommendCall = recipeServices.getCommendRecipe(body);
        recommendCall.enqueue(new Callback<RecommendResponse>() {
            @Override
            public void onResponse(Call<RecommendResponse> call, Response<RecommendResponse> response) {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(() -> {
                        binding.recommendTv.setText(response.body().getData().getName());
                        String pic = response.body().getData().getAvatar();
                        Glide.with(getActivity()).load(pic).into(binding.recommendImg);
                        binding.recommendImg.setOnClickListener(v -> {
                            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
                            intent.putExtra("id",response.body().getData().getId());
                            startActivity(intent);
                        });
                        initRecommend();
                    });
                }
            }
            @Override
            public void onFailure(Call<RecommendResponse> call, Throwable t) {
                Log.d("ShoppingFragment", "今日推荐加载失败");
            }
        });
    }

    private void getCartRequest(int userId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String , Integer> map = new HashMap<>();
        map.put("user_id",userId);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        CartServices cartServices = retrofit.create(CartServices.class);
        call = cartServices.getCartDetail(token,requestBody);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.body() != null && response.body().getData() != null){
                    binding.noItem.setVisibility(View.GONE);
                    double price = 0;
                    List<CartResponse.DataBean.InfoBean> infoList = new ArrayList<>(response.body().getData().getInfo());
                    for(CartResponse.DataBean.InfoBean dataBean : infoList){
                        price = 0;
                        String[] portion = dataBean.getMultiple().split(",");
                        String[] weight = dataBean.getFood_weight_list().split(",");
                        ingredient.clear();
                        sideIngredient.clear();
                        Log.d("portion",portion.length+"");
                        for(int i = 0; i < portion.length; i++){
                            Log.d("portion",dataBean.getFood().size()+" "+i);
                            int major = dataBean.getFood().get(i).getMajor();
                            if(portion[i].equals("0"))continue;
                            Ingredient in = new Ingredient(
                                    dataBean.getFood().get(i).getIngredient(),
                                    weight[i],
                                    dataBean.getFood().get(i).getId(),
                                    dataBean.getFood().get(i).getPrice());
                            in.setPortion(Integer.parseInt(portion[i]));
                            price += dataBean.getFood().get(i).getPrice() * Integer.parseInt(portion[i]);
                            if(major == 1){
                                ingredient.add(new Ingredient(in));
                            }
                            else if(major == 0){
                                sideIngredient.add(new Ingredient(in));
                            }
                        }
                        //解析recipe
                        list.add(new RecipeBean(dataBean.getId(),
                                dataBean.getName(),
                                dataBean.getTags(),
                                price,
                                dataBean.getAvatar(),
                                new ArrayList<>(ingredient),
                                new ArrayList<>(sideIngredient),
                                new ArrayList<>(step)));
                    }
                }
                requireActivity().runOnUiThread(()->initView());
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void countPrice(){
        totalPrice = 0;
        for(int i = 0 ; i < list.size();i++){
            totalPrice+=list.get(i).getPrice();
        }

    }

    private void initRecommend(){
        binding.recommend.setVisibility(View.VISIBLE);
        binding.recommendTitle.setVisibility(View.VISIBLE);
    }

    private void initView(){
        countPrice();
        String str = "共有"+list.size()+"件菜品";
        String price = "￥"+df.format(totalPrice);
        binding.shoppingRecipeTotal.setText(str);
        binding.shoppingCarTotal.setText(price);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext()){
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        if(list.size() == 0)binding.remarkAdd.setVisibility(View.GONE);
        else binding.remarkAdd.setVisibility(View.VISIBLE);
        binding.shoppingCarRv.setLayoutManager(layoutManager);
        adapter = new ShoppingAdapter(list,getContext());
        binding.shoppingCarRv.setAdapter(adapter);
        binding.loading.setVisibility(View.GONE);
        if (list.size() == 0)binding.noItem.setVisibility(View.VISIBLE);
        else binding.noItem.setVisibility(View.GONE);
        binding.submitButton.setOnClickListener(view -> {
            if(list.size() != 0) postRequest(userId);
            else Toast.makeText(getContext(),"您的购物车还没有东西哦！",Toast.LENGTH_SHORT).show();
        });
        binding.remarkAdd.setOnClickListener(view -> {
            if(list.size() != 0){
                OrderDialog dialog = new OrderDialog(this);
                dialog.show(((MainActivity)getActivity()).getSupportFragmentManager(),"tag");
            }
            else Toast.makeText(getContext(),"您的购物车还没有东西哦！",Toast.LENGTH_SHORT).show();
        });
        //管理购物车功能暂不实现
        /*binding.manageButton.setOnClickListener(view -> {
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
                String price1 = "￥"+df.format(totalPrice);
                String str1 = "共有"+list.size()+"件菜品";
                adapter.open = false;
                adapter.notifyDataSetChanged();
                binding.shoppingCarTotal.setText(price1);
                binding.shoppingRecipeTotal.setText(str1);
                binding.manageButton.setText("管理");
                binding.total.setVisibility(View.VISIBLE);
                binding.submitButton.setVisibility(View.VISIBLE);
                binding.shoppingCarTotal.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"已经删除了"+(t-list.size())+"个商品",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void delete(){
        int i = 0;
        for (int position:adapter.getMap().keySet()) {
            list.remove(position-i);
            i++;
        }
    }

    public void updateRemark(String str){
        this.remark = str;
        if(remark == null)remark = "";
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null && call.isExecuted())
            call.cancel();
    }
}