package com.example.caigouapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.caigouapp.databinding.ActivitySignupBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RequestServices;
import com.example.caigouapp.utils.SpUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private Context mContext;
    private String account;
    private String password;
    private String password_confirm;
    private SpUtil sp = SpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = binding.userAccount.getText().toString();
                password = binding.userPwd.getText().toString();
                password_confirm = binding.userPwdConfirm.getText().toString();
                //SpUtil.getInstance().setString(this, account, password);
                String checkcode = sp.getString(account,"");
                if(account == null){
                    Toast.makeText(SignupActivity.this,"账号为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(account != null && password == null){
                    Toast.makeText(SignupActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(account != null && password != null && password_confirm == null){
                    Toast.makeText(SignupActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                }
                if(account != null && password != password_confirm ){
                    Toast.makeText(SignupActivity.this,"两次输入密码不一致！请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(checkcode != null){
                    Toast.makeText(SignupActivity.this,"用户已存在！",Toast.LENGTH_SHORT).show();
                }
                else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL_BASE)
                            .build();
                    RequestServices requestServices = retrofit.create(RequestServices.class);
                    Call<ResponseBody> call = requestServices.getSignupUser(account,password);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccess()){
                                Toast.makeText(SignupActivity.this,"注册成功！请登陆",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("SignupActivity","error");
                        }
                    });

                }
            }
        });
        binding.findPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
