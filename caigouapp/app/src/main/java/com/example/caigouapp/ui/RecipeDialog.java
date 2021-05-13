package com.example.caigouapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RecipeDialog extends DialogFragment {
    private DialogUploadRecipeBinding binding;
    private ArrayList<Ingredient> uploadList = new ArrayList<>();
    private RecipeBean recipe;
    private UploadIngredientAdapter adapter;

    public RecipeDialog(ArrayList<Ingredient> list,RecipeBean data){
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

    private void initView(){
        adapter = new UploadIngredientAdapter(uploadList,recipe.getId());
        binding.uploadRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.uploadRv.setAdapter(adapter);
        binding.cancelButton.setOnClickListener(view -> {
            this.dismiss();
        });
        binding.uploadButton.setOnClickListener(view -> {
            StringBuilder multiple = new StringBuilder();
            StringBuffer foodIdList = new StringBuffer();
            double price = 0;
            recipe.setIngredient(adapter.getUploadList());
            for(int i = 0 ;i < recipe.getIngredient().size();i++){
                Ingredient ingredient = recipe.getIngredient().get(i);
                multiple.append(ingredient.getPortion());
                foodIdList.append(ingredient.getId());
                price += ingredient.getPrice() * ingredient.getPrice();
                if(i < recipe.getIngredient().size() - 1){
                    multiple.append(",");
                    foodIdList.append(",");
                }
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.URL_BASE)
                    .build();
            CartServices cartServices = retrofit.create(CartServices.class);
            //userID还未确定给入
            Call<CommonResponse> call = cartServices.postCustomRecipe(multiple.toString(),price,foodIdList.toString(),recipe.getId(),recipe.getId());
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
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
