package com.example.caigouapp;

import android.util.Log;

import androidx.test.espresso.base.MainThread;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(LoginActivity.class);
    public LoginActivity loginActivity;
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @MainThread
    @Test
    public void testLogin() {
        loginActivity = (LoginActivity) mActivityRule.getActivity();
        loginActivity.runOnUiThread(()->{
            loginActivity.binding.userAccount.setText("cy");
            loginActivity.binding.userPwd.setText("123");
            loginActivity.setClick();
            loginActivity.binding.btn.performClick();
        });
        Map<String, Object> params = new HashMap<>();
        params.put("phone", "cy");
        params.put("password", "123");
        UserServices userServices = retrofit.create(UserServices.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                ,new Gson().toJson(params));
        Call<UserResponse> call = userServices.getPostUser(body);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                assertEquals("200",loginActivity.responseCode);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }
}
