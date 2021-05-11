package com.example.caigouapp;

import java.io.Serializable;
import java.util.List;

public class RecipeBean implements Serializable {
    private String name;
    private String intro;
    private double price;
    private List<Ingredient> ingredient;
    private List<Ingredient> side_ingredient;
    private List<String> step;

    public RecipeBean(String name, String intro, double price, List<Ingredient> ingredient, List<Ingredient> side_ingredient, List<String> step) {
        this.name = name;
        this.intro = intro;
        this.price = price;
        this.ingredient = ingredient;
        this.side_ingredient = side_ingredient;
        this.step = step;
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

    public List<String> getStep() {
        return step;
    }

    public void setStep(List<String> step) {
        this.step = step;
    }




}
