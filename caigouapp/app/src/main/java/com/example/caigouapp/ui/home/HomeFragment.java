package com.example.caigouapp.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.caigouapp.R;
import com.example.caigouapp.databinding.FragmentHomeBinding;
import com.example.caigouapp.ui.SearchActivity;
import com.example.caigouapp.utils.StatusBarUtils;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        initView();
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
        binding.clTop.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }
}