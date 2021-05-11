package com.example.caigouapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivitySignupBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
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
                String spAccount = SpUtil.getInstance().getString("account","");
                String spPwd = SpUtil.getInstance().getString("password","");
                //Toast.makeText(SignupActivity.this,"*"+spPwd,Toast.LENGTH_SHORT).show();
                if(account.equals("")){
                    Toast.makeText(SignupActivity.this,"账号为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else if(!account.equals("") && password.equals("")){
                    Toast.makeText(SignupActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else if(!account.equals("") && !password.equals("") && password_confirm.equals("")){
                    Toast.makeText(SignupActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                }
                else if(!account.equals("") && !password.equals(password_confirm) ){
                    Toast.makeText(SignupActivity.this,"两次输入密码不一致！请重新输入",Toast.LENGTH_SHORT).show();
                }
                else if(!binding.rb.isChecked()){
                    Toast.makeText(SignupActivity.this,"请勾选阅读相关文件",Toast.LENGTH_SHORT).show();
                }
                else if(spAccount.equals(account)){
                    Toast.makeText(SignupActivity.this,"用户已存在！",Toast.LENGTH_SHORT).show();
                }
                else{
                    //write to isSuccessful
                        sp.putString("account",account);
                        sp.putString("password",password);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL_BASE)
                            .build();
                    UserServices userServices = retrofit.create(UserServices.class);
                    Call<UserResponse> call = userServices.getSignupUser(account,password);
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful() && response.body().getCode().equals("200")){
                                Toast.makeText(SignupActivity.this,"注册成功！即将跳转登陆",Toast.LENGTH_SHORT).show();

                                mHandler.sendEmptyMessageDelayed(0,1500);
                                //Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                //startActivity(intent);
                                //finish();
                                finishActivity(1);
                            }
                            else if (response.body().getCode().equals("1001")){
                                Toast.makeText(SignupActivity.this,"用户已存在，请直接登录",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.d("SignupActivity error:",t.toString());
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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            toLoginActivity();
            super.handleMessage(msg);
        }
    };

    public void toLoginActivity(){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
