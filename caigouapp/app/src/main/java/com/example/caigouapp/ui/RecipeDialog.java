package com.example.caigouapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.databinding.DialogUploadRecipeBinding;
import com.example.caigouapp.http.CartServices;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.ui.adapter.UploadIngredientAdapter;
import com.example.caigouapp.ui.shopping.ShoppingFragment;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecipeDialog extends DialogFragment {
    private DialogUploadRecipeBinding binding;
    private ArrayList<Ingredient> uploadList = new ArrayList<>();
    private RecipeBean recipe;
    private UploadIngredientAdapter adapter;
    private int totalSize;
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4In0.KD68i4Quc8pI2bD9NbT0nhJlrYcOb-I8X07LG7DNwJs";
    private int userId = 3;

    public RecipeDialog(ArrayList<Ingredient> list,RecipeBean data,int size){
        this.totalSize = size;
        uploadList.addAll(list);
        recipe = new RecipeBean(data);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Window window = getDialog().getWindow();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 遮罩层透明度
        getDialog().getWindow().setDimAmount(0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (dm.widthPixels * 0.95);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().setAttributes(params);
        binding = DialogUploadRecipeBinding.inflate(getLayoutInflater());
        initView();
        return binding.getRoot();
    }

    private String getMultiply(ArrayList<Ingredient> list,int size){
        String[] multiply = new String[size];
        StringBuilder builder = new StringBuilder();
        Arrays.fill(multiply, "0");
        for(Ingredient in : list){
            multiply[in.getIndex()] = String.valueOf(in.getPortion());
            Log.d("tag",in.getId()+" "+in.getIndex());
        }
        for (String s : multiply) {
            builder.append(s);
            builder.append(",");
        }
        return builder.toString();
    }

    private void initView(){
        adapter = new UploadIngredientAdapter(uploadList,recipe.getId());
        binding.uploadRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.uploadRv.setAdapter(adapter);
        binding.cancelButton.setOnClickListener(view -> {
            this.dismiss();
        });
        binding.uploadButton.setOnClickListener(view -> {
            String multiple;
            StringBuilder foodIdList = new StringBuilder();
            double price = 0;
            recipe.setIngredient(adapter.getUploadList());
            multiple = getMultiply((ArrayList<Ingredient>) recipe.getIngredient(),totalSize);
            for(int i = 0 ;i < recipe.getIngredient().size();i++){
                Ingredient ingredient = recipe.getIngredient().get(i);
                foodIdList.append(ingredient.getId());
                price += ingredient.getPrice() * ingredient.getPortion();
                if(i < recipe.getIngredient().size() - 1){
                    foodIdList.append(",");
                }
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CartServices cartServices = retrofit.create(CartServices.class);
            //userID还未确定给入
            HashMap<String , Object> map = new HashMap<>();
            map.put("Multiple",multiple);
            map.put("price",price);
            map.put("food_id_list",foodIdList.toString());
            map.put("userId",userId);
            map.put("id",recipe.getId());
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
            Call<CommonResponse> call = cartServices.postCustomRecipe(token,requestBody);
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call,Response<CommonResponse> response) {
                    Toast.makeText(getContext(),"已经添加到购物车啦",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("shoppingCar",1);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
