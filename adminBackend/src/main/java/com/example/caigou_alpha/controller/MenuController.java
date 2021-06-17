package com.example.caigou_alpha.controller;

import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.FileUtils;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.dao.CustomMenuDao;
import com.example.caigou_alpha.dao.TagDao;
import com.example.caigou_alpha.entity.*;
import com.example.caigou_alpha.service.MenuService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
    @Resource
    private TagDao tagDao;
    @Resource
    private CustomMenuDao customMenuDao;
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


    @UserLoginToken
    @PostMapping("/addMenuDetail")
    public Result<Integer> addDetail(@RequestBody MenuDI menuDI){
        return Result.success(menuService.addMenuDetail(menuDI));
    }

    @UserLoginToken
    @GetMapping("/getAllTags")
    public Result<List<Tags>> getAllTags(){
        return Result.success(tagDao.findAll());
    }


//    @UserLoginToken
//    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)
//    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
//
//        if(file != null){
//            return menuService.upload(file);
//        }
//        return "文件不存在";
//    }


    @Value("${web.upload-path}")
    private String path;

    /**
     *
     * @param file 上传的文件
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file){
        //1定义要上传文件 的存放路径
//        String localPath="E:/image";
        String localPath = "/www/javaweb/zhpart/upload";
        String virtual = "/pics";
        //2获得文件名字
        String fileName=file.getOriginalFilename();
        //2上传失败提示
        String warning="";
//        String url = "http://106.53.148.37:8083" + localPath + "/"+fileName;
        String newFileName = FileUtils.upload(file, localPath, fileName);
        if(newFileName != null){
            warning ="http://106.53.148.37:3001" + virtual +"/"+ newFileName ;
//            //上传成功
//            warning="上传成功";

        }else{
            warning="上传失败";
        }
        System.out.println(warning);
        return warning;
    }

    @UserLoginToken
    @PutMapping("/changeCart")
    public Result changeCart(@RequestParam("menuDelete") String CustomIdListToDelete,@RequestParam("userId") Integer userId){
        String CustomIdList = customMenuDao.customMenuList(userId);
        String[] menuDelete = CustomIdListToDelete.split(",");

        for(String s:menuDelete){
            CustomIdList = CustomIdList.replace(s+",","");
            customMenuDao.deleteMenuSonRow(Integer.parseInt(s));
        }
        customMenuDao.changeCart(CustomIdList,userId);
        return Result.success();
    }
}
