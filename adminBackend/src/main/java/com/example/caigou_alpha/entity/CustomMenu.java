package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class CustomMenu {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFood_id_list() {
        return food_id_list;
    }

    public void setFood_id_list(String food_id_list) {
        this.food_id_list = food_id_list;
    }

    public String getMultiple_list() {
        return Multiple_list;
    }

    public void setMultiple_list(String multiple_list) {
        Multiple_list = multiple_list;
    }

    @Transient
    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer menu_id;
    private double price;
    private String food_id_list;
    private String Multiple_list;

    @Transient
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    @Transient
    private String menuName;
//    private String address;

    @Transient
    @OneToMany
    private List<Food> foodList;

}
