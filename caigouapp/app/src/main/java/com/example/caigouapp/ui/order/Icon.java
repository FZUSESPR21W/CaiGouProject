package com.example.caigouapp.ui.order;

public class Icon {
    private int iId;//图片本地id
    private String iName;
    private String method;
    private double price;//单价
    private int piece;//这个菜买了多少份
    private int sourceMenuId;//源菜谱id

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public int getSourceMenuId() {
        return sourceMenuId;
    }

    public void setSourceMenuId(int sourceMenuId) {
        this.sourceMenuId = sourceMenuId;
    }

    public Icon() {
    }

    public Icon(int iId, String iName) {
        this.iId = iId;
        this.iName = iName;
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
