package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.UserOrderDao;
import com.example.caigou_alpha.entity.User;
import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Resource//获得将其他部分资源引入
    private UserOrderDao userOrderDao;

    public Page<UserOrder> findPageStatus(Integer pageNum, Integer pageSize,Integer status){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return userOrderDao.findAllStatus(status,pageable);
    }

    public Page<UserOrder> findPage(Integer pageNum, Integer pageSize){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return userOrderDao.findAll(pageable);
    }

    public Page<UserOrder> findPageOrderId(Integer pageNum, Integer pageSize,Integer status,Integer orderId){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return userOrderDao.findAllOrderId(status,orderId,pageable);
    }

    public Page<UserOrder> findPageOrderIdAll(Integer pageNum, Integer pageSize,Integer orderId){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return userOrderDao.findAllOrderIdAll(orderId,pageable);
    }

    public int changeStatus(Integer orderId){
        Date nowdate=new Date();
        //转换时间格式
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userOrderDao.selectOneOrder(orderId).setDeliverytime(Timestamp.valueOf(simpleDate.format(nowdate)).toString());
        return userOrderDao.changeStatus(orderId);
    }
}
