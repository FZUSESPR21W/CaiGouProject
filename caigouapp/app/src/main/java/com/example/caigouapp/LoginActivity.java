package com.example.caigouapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivityLoginBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.NullOnEmptyConverterFactory;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String account;
    private String password;
    private SpUtil sp = SpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Glide.with(this).load(R.drawable.vegetabledoge).into(binding.img);
        String spAccount = SpUtil.getInstance().getString("account","");
        String spPwd = SpUtil.getInstance().getString("password","");
        binding.userAccount.setText(spAccount);
        binding.userPwd.setText(spPwd);
        binding.userAccount.clearFocus();
        binding.userPwd.clearFocus();
        binding.signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivityForResult(intent,1);
        });
        binding.btn.setOnClickListener(view -> {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
            account = binding.userAccount.getText().toString();
            password = binding.userPwd.getText().toString();

            if(account.equals("")){
                Toast.makeText(LoginActivity.this,"账号为空，请重新输入",Toast.LENGTH_SHORT).show();
            }
            else if(!account.equals("") && password.equals("")){
                Toast.makeText(LoginActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
            }
            else{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserServices userServices = retrofit.create(UserServices.class);
                HashMap<String,String> map = new HashMap<>();
                map.put("phone",account);
                map.put("password",password);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
                Call<UserResponse> call = userServices.getPostUser(body);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful() && response.body().getCode() == null){
                            sp.putString("account",account);
                            sp.putString("password",password);
                            sp.putString("token",response.body().getToken());
                            sp.putInt("id",response.body().getData().getId());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (response.body().getCode().equals("1003")){
                            Toast.makeText(LoginActivity.this,"密码错误，登录失败",Toast.LENGTH_SHORT).show();
                        }
                        else if (response.body().getCode().equals("1002")){
                            Toast.makeText(LoginActivity.this,"用户不存在，请重新注册",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            runOnUiThread(() -> {
                                Toast.makeText(LoginActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("LoginActivity error:",t.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                        });
                    }

                });

            }
        });
    }


}
