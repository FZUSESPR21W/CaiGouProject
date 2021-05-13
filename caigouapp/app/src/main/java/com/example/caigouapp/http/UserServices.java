package com.example.caigouapp.http;

import com.example.caigouapp.data.UserResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServices {

    @POST("user/login")
    Call<UserResponse> getPostUser(@Body RequestBody body);

    @POST("user/register")
    Call<UserResponse> getSignupUser(@Body RequestBody body);

}
