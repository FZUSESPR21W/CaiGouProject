package com.example.caigou_alpha.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuInfo {
    @OneToOne
    private CustomMenu customMenu;
    @OneToOne
    private MenuFood menuFood;

    List<String> foodListNum = new ArrayList<>();
    List<String> standardList = new ArrayList<>();

}
