package com.example.caigouapp;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;

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


    public Ingredient(String name, String weight) {
        this.name = name;
        this.weight = weight;
        this.portion = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
