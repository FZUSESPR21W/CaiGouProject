package com.example.caigouapp.ui.order;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id")
    private int id;
    @SerializedName("ingredient")
    private String ingredient;
    @SerializedName("price")
    private double price;
    @SerializedName("major")
    private int major;
    @SerializedName("standard_weight")
    private String standardWeight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public String getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(String standardWeight) {
        this.standardWeight = standardWeight;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", ingredient='" + ingredient + '\'' +
                ", price=" + price +
                ", major=" + major +
                ", standardWeight='" + standardWeight + '\'' +
                '}';
    }
}
