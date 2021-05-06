package com.example.caigouapp.data;

public class RecipeBean {
    private String name;
    private String method;
    private int status;
    private String tags;
    private String avatar;
    private String foodList;
    private double price;

    public RecipeBean(String name, String method, int status, String foodList, String tags, String avatar, double price) {
        this.name = name;
        this.method = method;
        this.status = status;
        this.foodList = foodList;
        this.tags = tags;
        this.avatar = avatar;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFoodList() {
        return foodList;
    }

    public void setFoodList(String foodList) {
        this.foodList = foodList;
    }
}
