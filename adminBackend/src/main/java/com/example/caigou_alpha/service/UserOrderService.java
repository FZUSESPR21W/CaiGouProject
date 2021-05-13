package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.*;
import com.example.caigou_alpha.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserOrderService {

    @Resource
    private UserOrderDao userOrderDao;
    @Resource
    private CustomMenuDao customMenuDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private UserDao userDao;
    public  UserOrderInfoListAll getUserOrderInfo(){

//        List<UserOrder> user_order = userOrderDao.selectUserOrderByUserId(user_id);
        List<UserOrder> user_order = userOrderDao.findAll();
        //创建拥有该用户所有订单信息的对象
        UserOrderInfoListAll userOrderInfoListAll = new UserOrderInfoListAll();
        //获取一个订单的信息
        for (UserOrder userOrder : user_order) {
            UserOrderInfoList userOrderInfoList = new UserOrderInfoList();
            userOrderInfoList.setRemark(userOrder.getRemark());//备注
            userOrderInfoList.setOrderNumber(123456);//订单号
            userOrderInfoList.setStoreName("顺丰超市");
            userOrderInfoList.setOrderState(userOrder.getStatus());
            userOrderInfoList.setOrderCreatTime("2021-05-15  15:05:77");
            userOrderInfoList.setOrderServeTime("2021-05-15  15:55:77");
            userOrderInfoList.setAddress(userOrder.getAddress());
            userOrderInfoList.setPrice(userOrder.getPrice());
            userOrderInfoList.setPhone(userDao.findById(userOrder.getUser_id()).orElse(null).getPhone());
//            userOrderInfoList.setAddress(address.getAddress());
            //获取一个订单中的一个菜谱的信息
            String str = userOrder.getCustom_menuid_list();
            String[] A = str.split(",");
            for (String s : A) {
                int id = Integer.parseInt(s);
                CustomMenu custom_menu = customMenuDao.selectCustomMenuById(id);
                UserOrderInfo userOrderInfo =new UserOrderInfo();

                userOrderInfo.setPrice(custom_menu.getPrice());//price
                userOrderInfo.setMenuId(custom_menu.getMenu_id());
                userOrderInfo.setId(custom_menu.getId());
                Menu menu = menuDao.selectMenuById(custom_menu.getMenu_id());
                userOrderInfo.setAvatar(menu.getAvatar());
                userOrderInfo.setMethod(menu.getMethod());
                userOrderInfo.setName(menu.getName());
                userOrderInfo.setTags(menu.getTags());
                userOrderInfo.setMultiple(custom_menu.getMultiple_list());
                //获取一个菜谱的食材信息
                String str1 = custom_menu.getFood_id_list();
                String[] B = str1.split(",");
                for (String s1 : B) {
                    int ID = Integer.parseInt(s1);
                    Food f =menuDao.findByFoodId(ID);
                    userOrderInfo.getList().add(f);
                }
                //将一个菜谱加入到一个订单中
                userOrderInfoList.getInfo().add(userOrderInfo);

            }
            //将一个订单加入订单列表
            userOrderInfoListAll.getInfo().add(userOrderInfoList);
        }
        //返回订单列表
        return userOrderInfoListAll;
    }
}
