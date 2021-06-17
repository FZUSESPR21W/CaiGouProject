package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.CustomMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface CustomMenuDao extends JpaRepository<CustomMenu,Integer> {

//    /**
//     *根据菜谱ID查询自定义菜谱
//     * @param id
//     * @return
//     */
//    @Query(value ="select cm from Custom_Menu cm where cm.menu_id = :id")
//    Custom_Menu selectCustMenuByMid(@Param("id")Integer id);


    /**
     * 根据购物车的自定义菜谱ID查询自定义菜谱
     * @param custom_menuid
     * @return
     */
    @Query(value ="select cm from  CustomMenu  cm where cm.id = :custom_menuid")
    CustomMenu selectCustomMenuById(@Param("custom_menuid")Integer custom_menuid);


//    SET foreign_key_checks = 0;  // 先设置外键约束检查关闭
//
//    drop table mytable;  // 删除数据，表或者视图
//
//    SET foreign_key_checks = 1; // 开启外键约束检查，以保持表结构完整性

    @Modifying
    @Transactional
    @Query("delete from CustomMenu  customMenu where customMenu.menu_id = :id")
    Integer deleteMenuSonRow(@Param("id") Integer id);

    @Query("select cm.Multiple_list from CustomMenu  cm where  cm.id = :customMenuId")
    String findMultipleList(@Param("customMenuId") Integer id);

    @Query("select cm.food_id_list from CustomMenu  cm where  cm.id = :customMenuId")
    String findFoodList(@Param("customMenuId") Integer id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update cart set custom_menuid = ?1 where user_id =?2",nativeQuery = true)
    int changeCart(String newCustomList,Integer userId);

    @Query(value = "select custom_menuid from cart where  user_id = ?1",nativeQuery = true)
    String customMenuList(@Param("userId") Integer id);
}
