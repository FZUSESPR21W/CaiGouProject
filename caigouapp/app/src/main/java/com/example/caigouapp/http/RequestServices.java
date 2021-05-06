package com.example.caigouapp.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestServices {
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> getPostUser(@Field("phone") String phoneStr,@Field("password") String passwordStr);

    @FormUrlEncoded
    @POST("user/signup")
    Call<ResponseBody> getSignupUser(@Field("phone") String phoneStr,@Field("password") String passwordStr);

}
