package com.example.caigou_alpha.controller;


import com.example.caigou_alpha.annotation.UserLoginToken;
import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.UserOrder;
import com.example.caigou_alpha.entity.UserOrderInfoListAll;
import com.example.caigou_alpha.service.OrderService;
import com.example.caigou_alpha.service.UserOrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private UserOrderService userOrderService;

    /**
     * /order/findAll
     * 查询所有订单
     * @param pageNum(分页显示的页数）
     * @return Page<UserOrder>该页的内容
     */
    @UserLoginToken
    @GetMapping("/findAll")
    public Result<Page<UserOrder>> findAll(@RequestParam Integer pageNum){
        return Result.success(orderService.findPage(pageNum,5));
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

//    @GetMapping("/findAll2")
//    public UserOrderInfoListAll findAll3(){
//    }

    @UserLoginToken
    @GetMapping("/findOne")
    public UserOrder findOne(@RequestParam Integer id){
        return userOrderService.testPage(id);
    }

}
