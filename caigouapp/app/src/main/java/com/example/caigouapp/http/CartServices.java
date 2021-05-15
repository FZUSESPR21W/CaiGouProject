package com.example.caigouapp.http;

import com.example.caigouapp.data.CartResponse;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.RecipeDetailResponse;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CartServices {

    @POST("updata/custMenu")
    Call<CommonResponse> postCustomRecipe(@Header("token") String header,@Body RequestBody body);
                                          /*@Field("Multiple") String multiple
                                          @Field("price") Double price,
                                          @Field("food_id_list") String foodIdList,
                                          @Field("userId") Integer userId,
                                          @Field("id") Integer id */

    @POST("order")
    Call<CommonResponse> postOrder(@Header("token") String header, @Body RequestBody body);

    @POST("cart/list")
    Call<CartResponse> getCartDetail(@Header("token") String header,@Body RequestBody body);


}
