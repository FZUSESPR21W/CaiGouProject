package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.CustomMenuDao;
import com.example.caigou_alpha.dao.FoodDao;
import com.example.caigou_alpha.entity.CustomMenu;
import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomMenuService {
    @Resource
    private CustomMenuDao customMenuDao;
    @Resource
    private FoodDao foodDao;
    public CustomMenu findCustomMenuAndDetailById(CustomMenu customMenu){
        List<Food> foodList = new ArrayList<>();
        List<Integer> weightSplit  = new ArrayList<>();
        Integer id = customMenu.getId();
        Food food;
        customMenu = customMenuDao.selectCustomMenuById(id);
        String MulList;
        MulList = customMenuDao.findMultipleList(id);

        String foodListString;

        foodListString = customMenuDao.findFoodList(id);

        String[] foodListSplit = foodListString.split(",");
        String[] weightAllSplit = MulList.split(",");
        for(String s: weightAllSplit){
            if(Integer.parseInt(s) != 0) {
                weightSplit.add(Integer.parseInt(s));
            }
        }
        for(int i = 0;i < foodListSplit.length;i++){
            food = foodDao.findById(Integer.parseInt(foodListSplit[i])).orElse(null);
            food.setMultiple(weightSplit.get(i));
            foodList.add(food);
        }

        return customMenu;
    }
}
