package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserOrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String tags;
    private String method;
    private String avatar;
    private Double price;
    private Integer menuId;
    @OneToMany
    private List<Food> list = new ArrayList<Food>();
    private String Multiple;
}
