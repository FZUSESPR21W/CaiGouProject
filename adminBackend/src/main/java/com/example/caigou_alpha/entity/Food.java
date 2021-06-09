package com.example.caigou_alpha.entity;


import lombok.Data;

import javax.persistence.*;

//@Data
@Entity
public class Food{
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public String getStandard_weight() {
        return standard_weight;
    }

    public void setStandard_weight(String standard_weight) {
        this.standard_weight = standard_weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ingredient;
    private double price;
    private Integer major;
    private String standard_weight;

    @Transient
    public String getWeight() {
        return weight;
    }
//    @Transient
    public void setWeight(String weight) {
        this.weight = weight;
    }
//    @Transient
    private transient String weight;


    @Transient
    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    private transient  Integer multiple;

}
