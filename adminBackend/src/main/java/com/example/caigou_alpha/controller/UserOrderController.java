package com.example.caigou_alpha.controller;


import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.dao.UserOrderDao;
import com.example.caigou_alpha.entity.UserOrder;
import com.example.caigou_alpha.entity.UserOrderInfoListAll;
import com.example.caigou_alpha.service.OrderService;
import com.example.caigou_alpha.service.UserOrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/order")//给访问链接加个前缀
public class UserOrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private UserOrderDao userOrderDao;
    @Resource
    private UserOrderService userOrderService;

    /**
     * /order/findAll
     * 查询所有订单
     * @param pageNum(分页显示的页数）
     * @return Page<UserOrder>该页的内容
     */
    @UserLoginToken
    @GetMapping("/findAll")
    public Result<Page<UserOrder>> findAll(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return Result.success(orderService.findPage(pageNum,pageSize));
    }

    /**
     * /order/findAll1
     * @return
     */
    @UserLoginToken
    @GetMapping("/findAll1")
    public UserOrderInfoListAll findAll2(){
        return userOrderService.getUserOrderInfo();
    }

    //根据一个订单ID查询详情
    @UserLoginToken
    @GetMapping("/findOneDetail")
    public UserOrder findOne(@RequestParam Integer id){
        return userOrderService.testPage(id);
//        return userOrderService.testPage2(userOrderDao.selectOneOrder(7));
    }

    //非分页查询所有订单
    @UserLoginToken
    @GetMapping("/findPageTest")
    public List<UserOrder> findUserOrderPage(){

        return userOrderService.findUserOrderPage();
    }

    //分页查询，带有详情
    @UserLoginToken
    @GetMapping("/findPageOrder")
    public Page<UserOrder> findOrderPage( @RequestParam(required = true)Integer pageNum,@RequestParam(required = true)Integer pageSize){

        return userOrderService.findPageOrder(pageNum,pageSize);
    }
}
