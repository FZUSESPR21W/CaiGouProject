package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserOrderInfoListAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    private List<UserOrderInfoList> Info = new ArrayList<UserOrderInfoList>();
}
