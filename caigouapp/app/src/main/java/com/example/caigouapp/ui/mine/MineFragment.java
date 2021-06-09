package com.example.caigouapp.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.caigouapp.R;
import com.example.caigouapp.data.AddressBean;
import com.example.caigouapp.data.TagsBean;
import com.example.caigouapp.data.UserAddressResponse;
import com.example.caigouapp.data.UserAddressResponse.*;
import com.example.caigouapp.data.UserTagResponse;
import com.example.caigouapp.data.UserTagResponse.*;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.ui.AddAddressActivity;
import com.example.caigouapp.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MineFragment extends Fragment {

    private List<TagsBean> tagList = new ArrayList<>();
    private MineAdapter adapter;
    Call<UserAddressResponse> call;
    Call<UserTagResponse> call1;

//    地址管理代码
    private RecyclerView recyclerView;
    private List<AddressBean> addressList = new ArrayList<>();
    private MineAddressAdapter addressAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);

        ImageView tag = root.findViewById(R.id.tag_update);
        TextView userName = root.findViewById(R.id.user_name);
        ImageView address = root.findViewById(R.id.address_add);

        userName.setText(SpUtil.getInstance().getString("account","昵称"));

        tag.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TagChooseActivity.class);
            String tagChosen = "";
            for (TagsBean tagsBean : tagList) {
                tagChosen += tagsBean.getTag();
            }
            intent.putExtra("tagChosen",tagChosen);
            startActivity(intent);
        });

        address.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddAddressActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = root.findViewById(R.id.mine_tag_rv);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new MineAdapter(tagList, getActivity());
        recyclerView.setAdapter(adapter);

        //地址list
        recyclerView = (RecyclerView)root.findViewById(R.id.address_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        addressAdapter = new MineAddressAdapter(addressList, getActivity());
        recyclerView.setAdapter(addressAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        String account = SpUtil.getInstance().getString("account","");
        String token = SpUtil.getInstance().getString("token","");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //地址
        Log.d("account",account);
        Log.d("token",token);
        UserServices userServices = retrofit.create(UserServices.class);
        call = userServices.getUserAddress(token, account);
        call.enqueue(new Callback<UserAddressResponse>() {
            @Override
            public void onResponse(Call<UserAddressResponse> call, Response<UserAddressResponse> response) {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(() -> {
                        if (response.body().getAddress() != null){
                            addressList.clear();
                            addressList.addAll(response.body().getAddress());
                            addressAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<UserAddressResponse> call, Throwable t) {
                Log.d("MineFragment",t.getMessage());
            }
        });
        //标签
        call1 = userServices.getUserTags(token, account);
        call1.enqueue(new Callback<UserTagResponse>() {
            @Override
            public void onResponse(Call<UserTagResponse> call, Response<UserTagResponse> response) {
                if (response.isSuccessful()){
                    getActivity().runOnUiThread(() -> {
                        if (response.body().getTags() != null){
                            tagList.clear();
                            tagList.addAll(response.body().getTags());
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<UserTagResponse> call, Throwable t) {
                Log.d("MineFragment",t.getMessage());
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null && call.isExecuted())
            call.cancel();
        if (call1 != null && call1.isExecuted())
            call1.cancel();
    }
}