package com.example.caigouapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.databinding.ActivitySignupBinding;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;
import com.example.caigouapp.utils.StatusBarUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private String account;
    private String password;
    private String password_confirm;
    private SpUtil sp = SpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyApplication.getRefWatcher().watch(this);
        Glide.with(this).load(R.drawable.vegetabledoge).into(binding.img);
        binding.userAccount.clearFocus();
        binding.userPwd.clearFocus();
        binding.userPwdConfirm.clearFocus();
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.getInstance().putString("str","777");
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
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
                    Call<UserResponse> call = userServices.getSignupUser(body);
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"注册成功！即将跳转登陆",Toast.LENGTH_SHORT).show();
                                sp.putString("account",account);
                                sp.putString("password",password);
                                mHandler.sendEmptyMessageDelayed(0,1500);
//                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                                startActivity(intent);

                                //finishActivity(1);
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
        initStatusBar();//初始化状态栏
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(SignupActivity.this, R.color.white);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            toLoginActivity();
            super.handleMessage(msg);
            finish();
        }
    };

    public void toLoginActivity(){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
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
