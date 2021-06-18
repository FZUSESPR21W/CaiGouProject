package com.example.caigouapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivityLoginBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.EncryptUtil;
import com.example.caigouapp.utils.SpUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.Glide;
import com.example.caigouapp.utils.StatusBarUtils;
import com.example.push.helper.BasePushActivity;
import com.google.gson.Gson;
import com.umeng.message.PushAgent;

import java.util.HashMap;

public class LoginActivity extends BasePushActivity {
    public ActivityLoginBinding binding;
    public String account;
    public String password;
    public SpUtil sp = SpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String spAccount = SpUtil.getInstance().getString("account","");
        String spPwd = SpUtil.getInstance().getString("password","");
        if (!spPwd.equals("")){
            login(spAccount, spPwd);
            return;
        }
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Glide.with(this).load(R.drawable.vegetabledoge).into(binding.img);
        binding.userAccount.setText(spAccount);
        binding.userPwd.setText(spPwd);
        binding.userAccount.clearFocus();
        binding.userPwd.clearFocus();
        setClick();
        initStatusBar();//初始化状态栏
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(LoginActivity.this, R.color.white);
        }
    }

    public void setClick(){
        binding.signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivityForResult(intent,1);
        });
        binding.btn.setOnClickListener(view -> {
            account = binding.userAccount.getText().toString();
            password = binding.userPwd.getText().toString();
            password = EncryptUtil.md5(password);
            if(account.equals("")){
                Toast.makeText(LoginActivity.this,"账号为空，请重新输入",Toast.LENGTH_SHORT).show();
            }
            else if(!account.equals("") && password.equals("")){
                Toast.makeText(LoginActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
            }
            else{
                login(account, password);
            }
        });
    }

    private void login(String account, String password){
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
                    updateDeviceToken();
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

    private void updateDeviceToken(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserServices userServices = retrofit.create(UserServices.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("account",account);
        map.put("devicetoken", PushAgent.getInstance(this).getRegistrationId());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        Call<CommonResponse> call = userServices.updateDeviceToken(body);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) { }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.d("LoginActivity error:",t.toString());
                runOnUiThread(() -> {
                    Toast.makeText(LoginActivity.this,"deviceToken更新失败",Toast.LENGTH_SHORT).show();
                });
            }

        });
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent); //这一句必须的，否则Intent无法获得最新的数据
    }

    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

}
