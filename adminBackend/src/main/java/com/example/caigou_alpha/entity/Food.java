package com.example.caigou_alpha.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Food{
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
    private String weight;
//    private String weightList;//需要分割
}
