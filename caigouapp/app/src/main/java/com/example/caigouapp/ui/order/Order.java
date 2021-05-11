package com.example.caigouapp.ui.order;

import java.util.ArrayList;

public class Order {
    private String storeName;
    private boolean order_state;
    private double price;//总价
    private int piece;//件数，实际上是icons.size()
    private ArrayList<Icon> icons;//最后实现的时候实际上是 用户定义菜谱
    private String orderNumber;
    private String orderCreateTime;
    private String orderServeTime;//预计送达时间
    private String address;
    private String phone;

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

    public boolean isOrder_state() {
        return order_state;
    }

    public void setOrder_state(boolean order_state) {
        this.order_state = order_state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public ArrayList<Icon> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<Icon> icons) {
        this.icons = icons;
    }
}