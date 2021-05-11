package com.example.caigouapp;

import java.io.Serializable;
import java.util.List;

public class RecipeBean implements Serializable {
    private String name;
    private String intro;
    private double price;
    private String imageUrl;
    private List<Ingredient> ingredient;
    private List<Ingredient> side_ingredient;
    private List<Step> step;

    public RecipeBean(String name, String intro, double price,String url, List<Ingredient> ingredient, List<Ingredient> side_ingredient, List<Step> step) {
        this.name = name;
        this.intro = intro;
        this.price = price;
        this.imageUrl = url;
        this.ingredient = ingredient;
        this.side_ingredient = side_ingredient;
        this.step = step;
    }

    public RecipeBean(RecipeBean bean){
        this.name = bean.name;
        this.intro = bean.intro;
        this.price = bean.price;
        this.imageUrl = bean.imageUrl;
        this.ingredient = bean.ingredient;
        this.side_ingredient = bean.side_ingredient;
        this.step = bean.step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<Ingredient> getSide_ingredient() {
        return side_ingredient;
    }

    public void setSide_ingredient(List<Ingredient> side_ingredient) {
        this.side_ingredient = side_ingredient;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
