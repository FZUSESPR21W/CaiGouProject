package com.example.caigouapp.ui.mine;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.caigouapp.MainActivity;
import com.example.caigouapp.R;
import com.example.caigouapp.data.AddressBean;
import com.example.caigouapp.data.TagsBean;
import com.example.caigouapp.data.UserAddressResponse;
import com.example.caigouapp.data.UserAddressResponse.*;
import com.example.caigouapp.data.UserTagResponse;
import com.example.caigouapp.data.UserTagResponse.*;
import com.example.caigouapp.databinding.FragmentMineBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.ui.AddAddressActivity;
import com.example.caigouapp.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;
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
        binding = FragmentMineBinding.inflate(getLayoutInflater());

        binding.userName.setText(SpUtil.getInstance().getString("account","昵称"));

        binding.tagUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TagChooseActivity.class);
            String tagChosen = "";
            for (TagsBean tagsBean : tagList) {
                tagChosen += tagsBean.getTag();
            }
            intent.putExtra("tagChosen",tagChosen);
            startActivity(intent);
        });

        binding.addressAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddAddressActivity.class);
            startActivity(intent);
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        binding.mineTagRv.setLayoutManager(staggeredGridLayoutManager);

        adapter = new MineAdapter(tagList, getActivity());
        binding.mineTagRv.setAdapter(adapter);

        //地址list
        binding.addressRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        addressAdapter = new MineAddressAdapter(addressList, getActivity());
        binding.addressRv.setAdapter(addressAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable((Objects.requireNonNull(ContextCompat.getDrawable(requireContext(), R.drawable.line))));
        binding.addressRv.addItemDecoration(divider);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        binding.addressRv.setVisibility(View.GONE);
        binding.mineTagRv.setVisibility(View.GONE);
        binding.addressLoading.setVisibility(View.VISIBLE);
        binding.tagLoading.setVisibility(View.VISIBLE);
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
                    requireActivity().runOnUiThread(()-> {
                        binding.addressRv.setVisibility(View.VISIBLE);
                        binding.addressLoading.setVisibility(View.GONE);
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
                    requireActivity().runOnUiThread(()-> {
                        binding.mineTagRv.setVisibility(View.VISIBLE);
                        binding.tagLoading.setVisibility(View.GONE);
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
        if(addressAdapter.getSelectAddress() > -1)
            ((MainActivity)getActivity()).setAddressRequest(addressAdapter.getSelectAddress());
        if (call != null && call.isExecuted())
            call.cancel();
        if (call1 != null && call1.isExecuted())
            call1.cancel();
    }
}