package com.example.caigouapp.http;

import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.UserAddressResponse;
import com.example.caigouapp.data.UserResponse;
import com.example.caigouapp.data.UserTagResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserServices {

    @POST("user/login")
    Call<UserResponse> getPostUser(@Body RequestBody body);

    @POST("user/register")
    Call<UserResponse> getSignupUser(@Body RequestBody body);

    @GET("user/getUserTags/{account}")
    Call<UserTagResponse> getUserTags(@Header("token") String header, @Path("account") String account);

    @POST("user/addUserTags")
    Call<CommonResponse> addUserTags(@Header("token") String header, @Body RequestBody body);

    @GET("user/getUserAddress/{account}")
    Call<UserAddressResponse> getUserAddress(@Header("token") String header, @Path("account") String account);

    @POST("user/addUserAddress")
    Call<CommonResponse> addUserAddress(@Header("token") String header,@Body RequestBody body);
}
