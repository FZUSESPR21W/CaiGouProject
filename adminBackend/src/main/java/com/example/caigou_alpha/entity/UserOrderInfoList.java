package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserOrderInfoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String storeName;
    private Integer orderState;
    private Integer orderNumber;
    private String orderCreatTime;
    private String orderServeTime;
    private String Address;
    private String phone;
    private String remark;
    private Double price;
    @OneToMany
    private List<UserOrderInfo> Info = new ArrayList<UserOrderInfo>();


    public void setOrderCreatTime(String s) {
        this.orderCreatTime = s;
    }

    public void setOrderServeTime(String s) {
        this.orderServeTime = s;
    }
}
