package com.example.caigou_alpha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "menu_food")
@Entity
public class MenuFood {
    @Id
    private Integer id;
    private Integer menu_id;
    private String food_id_list;
    private String food_weight_list;
    public Integer getId() {
        return id;
    }

//    public List<Food> getFoodList() {
//        return foodList;
//    }
//
//    public void setFoodList(List<Food> foodList) {
//        this.foodList = foodList;
//    }
//
//    @OneToMany
//    private List<Food> foodList = new ArrayList<>();
//
//    public String getFoodListString() {
//        return foodListString;
//    }
//
//    public void setFoodListString(String foodListString) {
//        this.foodListString = foodListString;
//    }
//
//    public String getStandardList() {
//        return standardList;
//    }
//
//    public void setStandardList(String standardList) {
//        this.standardList = standardList;
//    }
//
//    private String foodListString;
//    private String standardList;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getFood_id_list() {
        return food_id_list;
    }

    public void setFood_id_list(String food_id_list) {
        this.food_id_list = food_id_list;
    }

    public String getFood_weight_list() {
        return food_weight_list;
    }

    public void setFood_weight_list(String food_weight_list) {
        this.food_weight_list = food_weight_list;
    }
}
