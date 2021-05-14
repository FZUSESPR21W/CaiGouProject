package com.example.caigou_alpha.service;

import com.example.caigou_alpha.entity.Menu;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@Service
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MenuServiceTest {
    @Autowired
   MenuService menuService;

    /**
     * 分页查询所有菜谱
     */
    @Rollback(value = true)
    @Test
    void findPage() {
        Menu menu;

        int pageNum = 1;
        int pageSize = 5;
        menu = menuService.findPage(1,5).get().findFirst().orElse(null);
        System.out.println(menu);
    }

    /**
     * 分页按名模糊查询所有菜谱
     */
    @Test
    void findLike() {
        Menu menu = new Menu();

        int pageNum = 1;
        int pageSize = 5;
        String name = "蛋";
//        String menu;
        menu = menuService.findLike(1,5,name).getContent().get(0);
        System.out.println(menu);
    }

    /**
     * 保存更改菜谱
     */
    @Rollback(value = true)
    @Test
    void save() {
        Menu menu = new Menu();
        menu.setAvatar("1");
        menu.setId(1);
        menu.setMethod("test");
        menu.setTags("testTag");
        menu.setName("testName");
        menu.setStatus(1);
        menuService.save(menu);
    }

    /**
     * 删除菜谱
     */
    @Rollback(value = true)
    @Test
    void del() {
        int id = 3;
        menuService.del(id);
    }
    /**
     * 分页按标签模糊查询所有菜谱
     */

    @Test
    void findLikeTags() {
        Menu menu = new Menu();

        int pageNum = 1;
        int pageSize = 5;
        String tag = "快手";
//        String menu;
        menu = menuService.findLikeTags(1,5,tag).getContent().get(0);
        System.out.println(menu);
    }
}
