package com.example.caigouapp;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.caigouapp.databinding.DialogUploadOrderBinding;
import com.example.caigouapp.ui.shopping.ShoppingFragment;
import com.example.caigouapp.utils.SpUtil;

public class OrderDialog extends DialogFragment {
    private DialogUploadOrderBinding binding;
    private String token = SpUtil.getInstance().getString("token",null);
    private int userId = SpUtil.getInstance().getInt("id",0);
    private ShoppingFragment fragment;

    public OrderDialog(ShoppingFragment fragment){
        this.fragment = fragment;
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
        getDialog().getWindow().setDimAmount((float) 0.3);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (dm.widthPixels * 0.95);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        getDialog().getWindow().setAttributes(params);
        binding = DialogUploadOrderBinding.inflate(getLayoutInflater());
        initView();
        return binding.getRoot();
    }

    private void initView(){
        binding.cancelButton.setOnClickListener(view -> {
            this.dismiss();
        });
        binding.uploadButton.setOnClickListener(view -> {
            String remark = binding.orderRemark.getText().toString();
            fragment.updateRemark(remark);
            this.dismiss();
        });
    }

}
