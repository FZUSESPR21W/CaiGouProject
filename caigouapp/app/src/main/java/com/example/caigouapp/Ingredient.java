package com.example.caigouapp;

import java.io.Serializable;

public class Ingredient implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    private String weight;
    private int portion;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Ingredient(String name, String weight, int id, double price) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.portion = 1;
        this.index = 0;
    }

    public Ingredient(Ingredient ingredient){
        this.price = ingredient.price;
        this.id = ingredient.id;
        this.name = ingredient.name;
        this.weight = ingredient.weight;
        this.portion = ingredient.portion;
        this.index = ingredient.index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
