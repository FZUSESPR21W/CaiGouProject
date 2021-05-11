package com.example.caigouapp.http;

import com.example.caigouapp.data.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServices {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> getPostUser(@Field("account") String phoneStr, @Field("password") String passwordStr);

    @FormUrlEncoded
    @POST("user/register")
    Call<UserResponse> getSignupUser(@Field("phone") String phoneStr,@Field("password") String passwordStr);

}
