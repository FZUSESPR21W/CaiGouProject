package com.example.caigou_alpha.controller;


import com.example.caigou_alpha.common.Result;
import com.example.caigou_alpha.entity.UserOrder;
import com.example.caigou_alpha.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/order")//给访问链接加个前缀
s
public class UserOrderController {
    @Resource
    private OrderService orderService;

    /**
     * /order/findAll/{pageNum}
     * 查询所有订单
     * @param pageNum(分页显示的页数）
     * @return Page<UserOrder>该页的内容
     */
    @GetMapping("/findAll/{pageNum}")
    public Result<Page<UserOrder>> findAll(@PathVariable Integer pageNum){
        return Result.success(orderService.findPage(pageNum,5));
    }
}
