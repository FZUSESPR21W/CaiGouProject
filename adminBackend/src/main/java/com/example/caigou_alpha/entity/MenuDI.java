package com.example.caigou_alpha.entity;


import java.util.List;

public class MenuDI {
    private String name;
    private int status;
    private String method;
    private String tags;
    private String avatar;
    private List<String> foodList;
    private List<String> weightList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public List<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<String> foodList) {
        this.foodList = foodList;
    }



    public List<String> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<String> weightList) {
        this.weightList = weightList;
    }

}
