//package com.example.caigou_alpha.service;
//
//import com.example.caigou_alpha.dao.StatisticsDao;
//import com.example.caigou_alpha.entity.Statistics;
//import com.example.caigou_alpha.entity.UserOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class StatisticsService {
//
//    @Autowired
//    private StatisticsDao statisticsDao;
//    @Resource
//    private Statistics statistics;
////    @Resource
////    private UserOrder userOrder;
//
//
//
//    public Statistics getTenRecent(){
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        String nowYM = sdf.format(new Date());
//        Integer day = new Date().getDay();
//        String now = nowYM + "-" + day.toString();
//        List<String> timeArray = new ArrayList<>();
//        List<Double> priceArray  = new ArrayList<>();
//        List<UserOrder> userOrderList;
//
//        Double max = 0.0;
//        Double min = 100000000.0;
////        UserOrder userOrder;
////        userOrderList = statisticsDao.findTenRecentOrder();
//
//        for(int i = 0;i < 10;i++){
//            Double tempProfit = 0.0;
//            Integer dayRecent = day - i;
//            String date = nowYM + "-" + dayRecent.toString();
//            timeArray.add(date);
//            userOrderList = statisticsDao.findOrderBytime(date);
//            for(UserOrder userOrder:userOrderList){
//                tempProfit += userOrder.getPrice();
//            }
//            priceArray.add(tempProfit);
//        }
//        statistics.setTimeTenRecent(timeArray);
//        statistics.setProfitTenRecent(priceArray);
//        for(Double price :priceArray){
//            if(price > max) max = price;
//            if(price < min) min = price;
//        }
//        statistics.setMaxProfit(max);
//        statistics.setMinProfit(min);
//        return statistics;
//    }
//}
