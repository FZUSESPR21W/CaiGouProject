package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuInfo {
//    @OneToOne
//    private CustomMenu customMenu;
//    @OneToOne
//    private MenuFood menuFood;
    @OneToOne
    private Menu menu;

    private int id;
    private String name;
    private int status;
    private String method;
    private String tags;
    private String avatar;

    @OneToMany
    private List<Food> foodList = new ArrayList<Food>();

//    List<String> foodListNum = new ArrayList<>();
//    List<String> standardList = new ArrayList<>();

}
