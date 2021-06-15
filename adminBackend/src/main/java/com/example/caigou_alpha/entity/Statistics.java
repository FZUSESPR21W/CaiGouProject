package com.example.caigou_alpha.entity;

import javax.persistence.Entity;
import java.util.List;


public class Statistics {
    private List<String> timeTenRecent;
    private List<Double> profitTenRecent;
    private List<Integer> orderNum;

    public List<Integer> getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(List<Integer> orderNum) {
        this.orderNum = orderNum;
    }



    public List<String> getTimeTenRecent() {
        return timeTenRecent;
    }

    public void setTimeTenRecent(List<String> timeTenRecent) {
        this.timeTenRecent = timeTenRecent;
    }

    public List<Double> getProfitTenRecent() {
        return profitTenRecent;
    }

    public void setProfitTenRecent(List<Double> profitTenRecent) {
        this.profitTenRecent = profitTenRecent;
    }

    public Double getMinProfit() {
        return minProfit;
    }

    public void setMinProfit(Double minProfit) {
        this.minProfit = minProfit;
    }

    public Double getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(Double maxProfit) {
        this.maxProfit = maxProfit;
    }

    private Double maxProfit;
    private Double minProfit;
}
