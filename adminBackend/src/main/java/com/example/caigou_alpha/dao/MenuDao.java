package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.MenuFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {
    @Query(value = "SELECT m FROM  Menu m  where m.name like %:name%")
    Page<Menu> findLikeDao(@Param("name") String name, Pageable pageable);

    /**
     * 查找该菜谱的食材列表
     * @param id
     * @return
     */
    @Query("select f.food_id_list from MenuFood f where f.menu_id = :id")
    String menu_food_info(@Param("id")Integer id);

//    /**
//     *根据菜谱ID查询菜谱
//     * @param id
//     * @return
//     */
//    @Query(value = "select m from Menu m where m.id = :id")
//    Menu findByMenu(@Param("id")Integer id);

    /**
     * 根据菜谱ID查询菜谱食关联表
     * @param id
     * @return
     */
    @Query(value = "select mf from MenuFood mf where mf.menu_id= :id")
    MenuFood findByMenuId(@Param("id")Integer id);

    /**
     * 根据食材ID获取食材信息
     * @param id
     * @return
     */
    @Query(value = "select f from Food f where f.id = :id")
    Food findByFoodId(@Param("id")Integer id);

    /**
     * 根据菜谱ID查询菜谱
     * @param id
     * @return
     */
    @Query(value = "select m from Menu  m where  m.id = :id")
    Menu selectMenuById(@Param("id")Integer id);
}
