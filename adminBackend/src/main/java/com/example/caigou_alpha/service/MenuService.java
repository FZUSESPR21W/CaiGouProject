package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.MenuDao;
import com.example.caigou_alpha.dao.MenuFoodDao;
import com.example.caigou_alpha.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;
    private MenuFoodDao menuFoodDao;

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
        menuFoodDao.deleteSonRow(id);
        menuDao.deleteById(id);
    }

    public Page<Menu> findLikeTags(Integer pageNum, Integer pageSize, String tags) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findLikeTagDao(tags,pageable);
    }
}
