package com.example.caigouapp.ui.order;

import android.database.Observable;

import com.example.caigouapp.data.OrderResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderRequest {

    @Headers("token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.9sswQvb4l2vmIGG6AX7hlAtEQ6H0MeK6A0Y4JeLCO9w")
    @POST("order/info")
    Call<OrderResponse> getPostCall(@Body RequestBody body);

    //Query是在url后加上?freefrom=123
    //field是存在request的body
    //想访问诸如http://httpbin.org/delay/123 使用@POST("delay/{delay}") 参数用@Path("delay") String delay
}
