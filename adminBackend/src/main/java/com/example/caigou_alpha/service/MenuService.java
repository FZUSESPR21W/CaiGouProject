package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.CustomMenuDao;
import com.example.caigou_alpha.dao.FoodDao;
import com.example.caigou_alpha.dao.MenuDao;
import com.example.caigou_alpha.dao.MenuFoodDao;
import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

//    @Resource
//    private Menu menu;
    @Resource
    private Food food;
    @Resource
    private List<Food> foodList;

    @Resource
    private FoodDao foodDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuFoodDao menuFoodDao;
    @Resource
    private CustomMenuDao customMenuDao;


    public MenuService(MenuFoodDao menuFoodDao) {
        this.menuFoodDao = menuFoodDao;
    }

    public Page<Menu> findPage(Integer pageNum, Integer pageSize){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findAll(pageable);
    }

    public Page<Menu> findLike(Integer pageNum, Integer pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findLikeDao(name,pageable);
    }

//    public Menu findLike1()

    /**
     * 新增和修改
     * 有ID则为修改
     * @param m
     */
    public void save(Menu m){

        menuDao.save(m);
    }

    public void del(Integer id){
        customMenuDao.deleteMenuSonRow(id);
//        menuFoodDao.deleteSonRow(id);
        menuDao.deleteById(id);
    }

    public Page<Menu> findLikeTags(Integer pageNum, Integer pageSize, String tags) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findLikeTagDao(tags,pageable);
    }


    //通过ID查找一个带有详情的菜谱
//    public Menu findMenuAndDetailById(Integer id){
//        menu = menuDao.selectMenuById(id);
//        String weightList;
//        weightList = menuDao.findWeightList(id);
//        menu.setWeightList(weightList);
//
//        String foodAll;
//        foodAll = menuDao.findFoodList(id);
//        menu.setFoodAll(foodAll);
//
//        String[] foodAllSplit = foodAll.split(",");
//        String[] weightAllSplit = weightList.split(",");
//
//        for(int i = 0;i < foodAllSplit.length;i++){
//            food = foodDao.findById(Integer.parseInt(foodAllSplit[i])).orElse(null);
//            food.setWeight(weightAllSplit[i]);
//            foodList.add(food);
//        }
//
////        for(String s:foodAllSplit){//填充每个Food的信息
////            foodList.add(foodDao.findById(Integer.parseInt(s)).orElse(null).setWeight(););
////        }
////        for(Food food:foodList){//填充每个Food的信息
////            foodDao.findById()
////        }
//        menu.setFoodList(foodList);
//        return menu;
//
//    }

}
