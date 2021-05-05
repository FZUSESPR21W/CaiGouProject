package com.example.caigou_alpha.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_order")
/*
*
* 订单表用于存放订单基本信息
* 关联：用户表，表示该用户下的订单；菜谱表，需要检索该菜谱的预览信息
*
 */
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//标识主键为自动递增
    private int id;
    private int user_id;
    private String remark;
    private int status;
    private String menus;
    private int store_id;
    private Double price;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
