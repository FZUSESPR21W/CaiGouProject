package com.example.caigouapp.http;

import com.example.caigouapp.data.RecipeDetailResponse;
import com.example.caigouapp.data.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RecipeServices {

    @FormUrlEncoded
    @POST("menu")
    Call<SearchResponse> searchRecipes(@Field("searchWord") String searchWord);

    @FormUrlEncoded
    @POST("menuInfo")
    Call<RecipeDetailResponse> getRecipeDetail(@Field("id") Integer id);
}
