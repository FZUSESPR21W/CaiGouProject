package com.example.caigouapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.caigouapp.databinding.ActivityLoginBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.RequestServices;
import com.example.caigouapp.utils.SpUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private Context mContext;
    private String account;
    private String password;
    private SpUtil sp = SpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = binding.userAccount.getText().toString();
                password = binding.userPwd.getText().toString();
                //SpUtil.getInstance().setString(this, account, password);
                String checkcode = sp.getString(account,"");
                if(account == null){
                    Toast.makeText(LoginActivity.this,"账号为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(account != null && password == null){
                    Toast.makeText(LoginActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(checkcode != null){
                    if (checkcode.equals(password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL_BASE)
                            .build();
                    RequestServices requestServices = retrofit.create(RequestServices.class);
                    Call<ResponseBody> call = requestServices.getPostUser(account,password);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccess()){
                                sp.clear();
                                sp.putString("account",account);
                                sp.putString("password",password);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("LoginActivity","error");
                        }
                    });

                }
            }
        });
    }
}
