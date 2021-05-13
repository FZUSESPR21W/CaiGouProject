package com.example.caigouapp.http;

import com.example.caigouapp.data.CartResponse;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.RecipeDetailResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CartServices {

    @FormUrlEncoded
    @POST("updata/custMenu")
    Call<CommonResponse> postCustomRecipe(@Field("Multiple") String multiple/*份数列表，添加成string，*/,
                                          @Field("price") Double price,
                                          @Field("food_id_list") String foodIdList,
                                          @Field("userId") Integer userId,
                                          @Field("id") Integer id/*这个id是菜谱id*/);

    @FormUrlEncoded
    @POST("order")
    Call<CommonResponse> postOrder(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("cart/list")
    Call<CartResponse> getCartDetail(@Field("userId") Integer userId);


}
