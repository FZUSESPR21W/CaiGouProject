package com.example.caigouapp.http;

import com.example.caigouapp.data.RecipeDetailResponse;
import com.example.caigouapp.data.RecommendResponse;
import com.example.caigouapp.data.SearchResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RecipeServices {

    @POST("menu")
    Call<SearchResponse> searchRecipes(@Body RequestBody requestBody);

    @POST("menuInfo")
    Call<RecipeDetailResponse> getRecipeDetail(@Header("token") String header, @Body RequestBody body);

    @POST("recommend")
    Call<RecommendResponse> getCommendRecipe(@Body RequestBody requestBody);
}
