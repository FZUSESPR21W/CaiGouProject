package com.example.caigouapp.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.caigouapp.R;
import com.example.caigouapp.data.SearchResponse;
import com.example.caigouapp.data.UserTagResponse;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RecipeServices;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MineFragment extends Fragment {

    private List<String> tagList = new ArrayList<>();
    private MineAdapter adapter;

//    地址管理代码
    private ListView listView;

    private List<String> listText;

    private MineAddressAdapter addressAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);

        initData();

        TextView textView = root.findViewById(R.id.tag_choose_tv);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TagChooseActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = root.findViewById(R.id.mine_tag_rv);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new MineAdapter(tagList, getActivity());
        recyclerView.setAdapter(adapter);

        //地址list
        listView = (ListView)root.findViewById(R.id.address_lv);
        addressAdapter = new MineAddressAdapter(listText, getActivity());
        listView.setAdapter(addressAdapter);

        return root;
    }

    private void initData() {
        //地址
        listText = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            listText.add("单选按钮标题" + i);
        }

        //标签
        String account = SpUtil.getInstance().getString("account","");
        String token = SpUtil.getInstance().getString("token","");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserServices userServices = retrofit.create(UserServices.class);
        Call<UserTagResponse> call = userServices.getUserTags(token, account);
        call.enqueue(new Callback<UserTagResponse>() {
            @Override
            public void onResponse(Call<UserTagResponse> call, Response<UserTagResponse> response) {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(() -> {
                        if (response.body().getTags() != null){
                            tagList.addAll(response.body().getTags());
                        }
                        adapter.notifyDataSetChanged();
                    });
                }
            }
            @Override
            public void onFailure(Call<UserTagResponse> call, Throwable t) {
                Log.d("MineFragment",t.getMessage());
            }
        });
    }
}