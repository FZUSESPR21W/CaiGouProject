package com.example.caigouapp.data;

public class RecommendResponse {

    private int code;
    private RecipeDetailResponse.DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RecipeDetailResponse.DataBean getData() {
        return data;
    }

    public void setData(RecipeDetailResponse.DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RecommendResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
