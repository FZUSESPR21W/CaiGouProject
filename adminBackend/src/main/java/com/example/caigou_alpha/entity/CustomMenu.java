package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CustomMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer menu_id;
    private double price;
    private String food_id_list;
    private String Multiple_list;
//    private String address;
//    @OneToMany
//    private List<Food> foodList = new ArrayList<Food>();

}
