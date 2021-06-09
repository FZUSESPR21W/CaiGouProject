package com.example.caigou_alpha.controller;

import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.Food;
import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.UserOrder;
import com.example.caigou_alpha.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/menu")//给访问链接加个前缀
public class MenuController {
    @Resource
    private MenuService menuService;

    /**
     *  /menu/findAll/{pageNum}
     * 所有菜谱的分页查询
     * @param pageNum
     * @return  Page<Menu>该页的内容
     */
    @UserLoginToken
    @GetMapping("/findAll")
    public Result<Page<Menu>> findAll(@RequestParam Integer pageNum){
        return Result.success(menuService.findPage(pageNum,5));
    }

    /**
     *  /menu/findLike
     * 菜谱的模糊分页查询，按名字模糊查询
     * @param pageNum
     * @param name
     * @return  Page<Menu>该页的内容
     */
    @UserLoginToken
    @GetMapping("/findLike")
    public Result<Page<Menu>> findMenuLike( @RequestParam(required = true)Integer pageNum,@RequestParam(required = true)String name){
        return Result.success(menuService.findLike(pageNum,5,name));
    }


    @UserLoginToken
    @GetMapping("/findLikeTag")
    public Result<Page<Menu>> findMenuLikeTags( @RequestParam(required = true)Integer pageNum,@RequestParam(required = true)String tag){
        return Result.success(menuService.findLikeTags(pageNum,5,tag));
    }

    /**
     * /menu/addMenu
     * 添加菜单，menu表中的每个字段都要填写因为数据库设计为非空
     * @param menu
     * @return
     */

    @UserLoginToken
    @PostMapping("/addMenu")
    public Result add(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.success();
    }

    /**
     * /menu/updateMenu
     * 修改菜单，id字段必须传入，未传入字段则默认为不更改
     * @param menu
     * @return
     */
    @UserLoginToken
    @PostMapping("/updateMenu")
    public Result update(@RequestBody  Menu menu){
        menuService.save(menu);
        return Result.success();
    }

    /**
     * /menu/deleteMenu/{id}
     * 根据id来删除菜谱
     * @param id
     * @return
     */
    @UserLoginToken
    @DeleteMapping("/deleteMenu")
    public Result delete(@RequestParam Integer id){
        menuService.del(id);
        return Result.success();
    }

    @UserLoginToken
    @GetMapping("/findMenuByIdDetail")
    public Result<Menu> findMenuByIdDetail(@RequestParam Integer id){
       return  Result.success(menuService.findMenuAndDetailById(id));
    }

    @UserLoginToken
    @GetMapping("/findDetailFoodList")
    public Result<List<Food>> findDetailFoodList(@RequestParam Integer id){
        return  Result.success(menuService.findDetailFoodList(id));
    }


}
