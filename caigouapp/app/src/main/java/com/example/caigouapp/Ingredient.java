package com.example.caigouapp;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private String portion;

    public Ingredient(String name, String portion) {
        this.name = name;
        this.portion = portion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }
}
