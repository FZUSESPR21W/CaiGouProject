package com.example.caigouapp.ui.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerMenu {
    @SerializedName("id")
    private int iId;//图片本地id，之后要改成cm的id
    @SerializedName("avatar")
    private String photoUrl;//图片链接
    @SerializedName("name")
    private String iName;//菜名
    @SerializedName("method")
    private String method;//做法
    @SerializedName("price")
    private double price;//单价
    @SerializedName("menuId")
    private int sourceMenuId;//源菜谱id
    @SerializedName("tags")
    private String tags;//菜品拥有的标签
    @SerializedName("multiple")
    private String multiple;
    @SerializedName("list")
    private List<Food> foods;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSourceMenuId() {
        return sourceMenuId;
    }

    public void setSourceMenuId(int sourceMenuId) {
        this.sourceMenuId = sourceMenuId;
    }

    public CustomerMenu() {
    }

    public CustomerMenu(int iId, String iName) {
        this.iId = iId;
        this.iName = iName;
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    @Override
    public String toString() {
        return "CustomerMenu{" +
                "iId=" + iId +
                ", photoUrl='" + photoUrl + '\'' +
                ", iName='" + iName + '\'' +
                ", method='" + method + '\'' +
                ", price=" + price +
                ", sourceMenuId=" + sourceMenuId +
                ", tags='" + tags + '\'' +
                ", multiple='" + multiple + '\'' +
                ", foods=" + foods +
                '}';
    }
}
