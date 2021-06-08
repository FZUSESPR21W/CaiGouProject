package com.example.caigouapp.ui.order;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Order {
    @SerializedName("id")
    private int id;
    @SerializedName("storeName")
    private String storeName;
    @SerializedName("orderState")
    private int orderState;
    @SerializedName("price")
    private double price;//总价
    @SerializedName("info")
    private ArrayList<CustomerMenu> cms;
    @SerializedName("orderNumber")
    private String orderNumber;
    @SerializedName("oderCreatTime")
    private String orderCreateTime;
    @SerializedName("oderServeTime")
    private String orderServeTime;//预计送达时间
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("remark")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<CustomerMenu> getCms() {
        return cms;
    }

    public void setCms(ArrayList<CustomerMenu> cms) {
        this.cms = cms;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderServeTime() {
        return orderServeTime;
    }

    public void setOrderServeTime(String orderServeTime) {
        this.orderServeTime = orderServeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderStateString(){
        switch (this.orderState){
            case 1:
                return "已送达";
            case 2:
                return "已取消";
            case 3:
                return "派送中";
            case 4:
                return "已下单";
        }
        return "错误状态";
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", orderState=" + orderState +
                ", price=" + price +
                ", cms=" + cms +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderServeTime='" + orderServeTime + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}