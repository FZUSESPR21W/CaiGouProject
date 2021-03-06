package com.example.caigou_alpha.service;

import com.example.caigou_alpha.common.ApiConstant;
import com.example.caigou_alpha.common.SpringUtil;
import com.example.caigou_alpha.config.MyPicConfig;
import com.example.caigou_alpha.dao.CustomMenuDao;
import com.example.caigou_alpha.dao.FoodDao;
import com.example.caigou_alpha.dao.MenuDao;
import com.example.caigou_alpha.dao.MenuFoodDao;
import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.MenuDI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService {

//    @Resource
//    private Menu menu;
//    @Resource
//    private Food food;
//    @Resource
//    private List<Food> foodList;
//
    @Resource
    private FoodDao foodDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private MenuFoodDao menuFoodDao;
    @Resource
    private CustomMenuDao customMenuDao;

//   Menu menu = (Menu)SpringUtil.getBean("Menu");
//   Food food = (Food)SpringUtil.getBean("Food");
//   FoodDao foodDao = (FoodDao) SpringUtil.getBean("FoodDao");
//   List<Food> foodList = (Food) SpringUtil.getBean("Food").;

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

    public void addMenuFood(List<Integer> foodIdList){

    }

    public void del(Integer id){

        customMenuDao.deleteMenuSonRow(id);
//        customMenuDao.deleteById(id);
//        menuFoodDao.deleteSonRow(id);
        menuDao.deleteById(id);
//        menuDao.deleteMenuSonRow(id);
    }

    public Page<Menu> findLikeTags(Integer pageNum, Integer pageSize, String tags) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findLikeTagDao(tags,pageable);
    }

    public Menu findMenuTest(Integer id){
        return menuDao.selectMenuById(id);
    }

    //通过ID查找一个带有详情的菜谱
    public Menu findMenuAndDetailById(Integer id){
        List<Food> foodList = new ArrayList<>();
        Menu menu;
        Food food;
        menu = menuDao.selectMenuById(id);
        String weightList;
        weightList = menuDao.findWeightList(id);

        String foodAll;
        foodAll = menuDao.findFoodList(id);

        String[] foodAllSplit = foodAll.split(",");
        String[] weightAllSplit = weightList.split(",");

        for(int i = 0;i < foodAllSplit.length;i++){
            food = foodDao.findById(Integer.parseInt(foodAllSplit[i])).orElse(null);
            food.setWeight(weightAllSplit[i]);
            foodList.add(food);
        }

        menu.setFoodList(foodList);
        return menu;
    }

    //根据菜谱ID查找该项的详情
    public List<Food> findDetailFoodList(Integer menuId){
        List<Food> foodList = new ArrayList<>();
        Menu menu;
        Food food;
        menu = menuDao.selectMenuById(menuId);
        String weightList;
        weightList = menuDao.findWeightList(menuId);

        String foodAll;
        foodAll = menuDao.findFoodList(menuId);
//
        String[] foodAllSplit = foodAll.split(",");
        String[] weightAllSplit = weightList.split(",");

        for(int i = 0;i < foodAllSplit.length;i++){
            food = foodDao.findById(Integer.parseInt(foodAllSplit[i])).orElse(null);
            food.setWeight(weightAllSplit[i]);
            foodList.add(food);
        }
        return foodList;
    }


    public Integer addMenuDetail(MenuDI menuDI){

        Menu menu = new Menu();
        String foodListString = "";
        String foodWeightString = "";
        menu.setStatus(1);
        menu.setName(menuDI.getName());
        menu.setMethod(menuDI.getMethod());
        menu.setTags(menuDI.getTags());
        menu.setAvatar(menuDI.getAvatar());

        menu = menuDao.save(menu);
        Integer menuNewId = menu.getId();
        List<String> foodList = menuDI.getFoodList();
        List<String> weightList = menuDI.getWeightList();
        for(String s:foodList){
            foodListString += ( s + ",");
        }
        for(String s:weightList){
            foodWeightString += ( s + ",");
        }
        return menuFoodDao.addOneRecord(menuNewId,foodListString,foodWeightString);
    }




    /**
     * @Description:    文件上传
     * @PARAM:
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;

        // 返回客户端 文件地址 URL
        String url = "http://106.53.148.37:8083"+"/www/javaweb/zhpart/upload/" + fileName;
//         String url = "localhost:8083"+"/upload/" + fileName;//本地

        File dest = new File(ApiConstant.UPLOAD_PATH + fileName);

//         File dest = new File(ApiConstant.DEV_UPLOAD_PATH + fileName);//本地

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        file.transferTo(dest);

//         return new ResultBuilder<>().setData(MapUtil.returnMap("url",url)).build();

        return url;
    }



}
