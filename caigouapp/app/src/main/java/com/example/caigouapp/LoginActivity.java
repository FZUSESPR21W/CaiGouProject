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
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        String spAccount = SpUtil.getInstance().getString("account","");
        String spPwd = SpUtil.getInstance().getString("password","");
        binding.userAccount.setText(spAccount);
        binding.userPwd.setText(spPwd);
        binding.signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
        binding.btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            /*account = binding.userAccount.getText().toString();
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
                        .build();
                UserServices userServices = retrofit.create(UserServices.class);
                Call<UserResponse> call = userServices.getPostUser(account,password);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful() && response.body().getCode().equals("200")){
                            sp.clear();
                            sp.putString("account",account);
                            sp.putString("password",password);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            runOnUiThread(() -> {
                                Toast.makeText(LoginActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("LoginActivity","error");
                        runOnUiThread(() -> {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        });
                    }

                });

            }*/
        });
    }
}
