package com.example.caigou_alpha.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
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
    private String custom_menuid_list;
    private int store_id;
    private Double price;
    private String address;
    private String phone;
    private String createtime;

//    public Date getCtime() {
//        return ctime;
//    }
//
//    public void setCtime(Date ctime) {
//        this.ctime = ctime;
//    }
//
//    private Date ctime;

    private String deliverytime;

    @OneToMany
    private transient List<CustomMenu> customMenuList;




    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }



    @Transient
    public List<CustomMenu> getCustomMenuList() {
        return customMenuList;
    }

    public void setCustomMenuList(List<CustomMenu> customMenuList) {
        this.customMenuList = customMenuList;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


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

    public String getCustom_menuid_list() {
        return custom_menuid_list;
    }

    public void setCustom_menuid_list(String menus) {
        this.custom_menuid_list = menus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
