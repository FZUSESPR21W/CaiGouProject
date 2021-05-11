package com.example.caigouapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.caigouapp.Ingredient;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.RecipeBean;
import com.example.caigouapp.databinding.DialogUploadRecipeBinding;
import com.example.caigouapp.ui.adapter.UploadIngredientAdapter;
import com.example.caigouapp.ui.shopping.ShoppingFragment;

import java.util.ArrayList;


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

        // 遮罩层透明度
        getDialog().getWindow().setDimAmount(0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().setAttributes(params);
        binding = DialogUploadRecipeBinding.inflate(getLayoutInflater());
        initView();
        return binding.getRoot();
    }

    private void initView(){
        adapter = new UploadIngredientAdapter(uploadList);
        binding.uploadRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.uploadRv.setAdapter(adapter);
        binding.cancelButton.setOnClickListener(view -> {
            this.dismiss();
        });
        binding.uploadButton.setOnClickListener(view -> {
            recipe.setIngredient(adapter.getUploadList());
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("shoppingCar",1);
            startActivity(intent);
        });
    }
}
