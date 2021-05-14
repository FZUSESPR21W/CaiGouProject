package com.example.caigouapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.caigouapp.LoginActivity;
import com.example.caigouapp.MainActivity;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivityAddAddressBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        initView();
        setContentView(binding.getRoot());
    }

    private void initView() {
        binding.btnArrowLeft.setOnClickListener(v -> finish());
        binding.btnSelected.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String phone = binding.etPhone.getText().toString();
            String address = binding.etAddress.getText().toString();
            if (name.equals("") || phone.equals("") || address.equals("")){
                Toast.makeText(AddAddressActivity.this,"请输入完整收货信息",Toast.LENGTH_SHORT).show();
                return;
            }
            addAddressRequest(name, phone, address);
        });
    }

    private void addAddressRequest(String name, String phone, String address) {
        int userId = SpUtil.getInstance().getInt("id",1);
        String token = SpUtil.getInstance().getString("token","");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserServices userServices = retrofit.create(UserServices.class);
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);
        map.put("user_id",userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        Call<CommonResponse> call = userServices.addUserAddress(token, body);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()){
                    finish();
                }else{
                    runOnUiThread(() -> {
                        Toast.makeText(AddAddressActivity.this,"添加地址失败",Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.d("AddressActivity error:",t.toString());
            }

        });
    }
}