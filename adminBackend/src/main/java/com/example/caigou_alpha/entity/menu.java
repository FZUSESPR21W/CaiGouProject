package com.example.caigou_alpha.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * 菜谱表用于存放菜谱基本信息
 * 关联：标签表：用于查找其相关的标签
 *
 */
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//标识主键为自动递增
    private int id;
    private String name;
    private int status;
    private String method;
    private String tags;
    private String avatar;

//    public List<Food> getFoodList() {
//        return foodList;
//    }
//
//    public void setFoodList(List<Food> foodList) {
//        this.foodList = foodList;
//    }
//
//    @OneToMany
//    private List<Food> foodList = new ArrayList<Food>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
