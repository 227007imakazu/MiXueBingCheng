package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/16 13:49
 * @注释
 */
@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 获取今日数据
     * @param begin
     * @param end
     * @return
     */
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {


        //新增用户数
        Integer newUser=getUserCount(begin, end);

        //查询每天的总订单数
        Integer orderCount = getOrderCount(begin, end, null);
        //查询每天的有效订单数
        Integer validOrderCount = getOrderCount(begin, end, Orders.COMPLETED);
        //订单完成率
        Double orderCompletionRate = 0.0;
        if(orderCount != 0){
            orderCompletionRate = validOrderCount.doubleValue() / orderCount;
        }
        //营业额
        Double turnover=getTurnOver(begin, end);

        //平均客单价=营业额 / 有效订单数
        Double unitPrice=0.0;
        if(validOrderCount!=0){
            unitPrice = turnover / validOrderCount;
        }



        return BusinessDataVO.builder()
                .orderCompletionRate(orderCompletionRate)
                .validOrderCount(validOrderCount)
                .turnover(turnover)
                .unitPrice(unitPrice)
                .newUsers(newUser)
                .build();
    }

    /**
     * 统计订单
     * @return
     */
    public OrderOverViewVO getOverviewOrders() {

        //全部订单
        Integer allOrders=getOrderCount(null,LocalDateTime.now(),null);
        //已取消订单
        Integer cancelledOrders=getOrderCount(null,LocalDateTime.now(),Orders.CANCELLED);
        //已完成订单
        Integer completedOrders=getOrderCount(null,LocalDateTime.now(),Orders.COMPLETED);
        //待派送订单
        Integer deliveredOrders=getOrderCount(null,LocalDateTime.now(),Orders.CONFIRMED);
        //待接单订单
        Integer waitingOrders=getOrderCount(null,LocalDateTime.now(),Orders.TO_BE_CONFIRMED);
        return OrderOverViewVO.builder()
                .allOrders(allOrders)
                .cancelledOrders(cancelledOrders)
                .completedOrders(completedOrders)
                .deliveredOrders(deliveredOrders)
                .waitingOrders(waitingOrders)
                .build();
    }

    /**
     * 统计菜品数量
     * @return
     */
    public DishOverViewVO getOverviewDishes() {
        //已停售菜品数量
        Integer discontinued=dishMapper.countByStatus(StatusConstant.DISABLE);
        //已起售菜品数量
        Integer sold=dishMapper.countByStatus(StatusConstant.ENABLE);
        return DishOverViewVO.builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }
    /**
     * 统计套餐数量
     * @return
     */
    public SetmealOverViewVO getOverviewSetmeals() {
        //已停售菜品数量
        Integer discontinued=setmealMapper.countByStatus(StatusConstant.DISABLE);
        //已起售菜品数量
        Integer sold=setmealMapper.countByStatus(StatusConstant.ENABLE);
        return SetmealOverViewVO.builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }

    /**
     * 获取一段时间的用户数
     * @param beginTime
     * @param endTime
     * @return
     */
    private Integer getUserCount(LocalDateTime beginTime, LocalDateTime endTime) {
        Map map = new HashMap();
        map.put("begin",beginTime);
        map.put("end", endTime);
        return userMapper.countByMap(map);
    }
    /**
     * 根据时间区间统计指定状态的订单数量
     * @param beginTime
     * @param endTime
     * @param status
     * @return
     */
    private Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status) {
        Map map = new HashMap();
        map.put("status", status);
        map.put("begin",beginTime);
        map.put("end", endTime);
        return orderMapper.countByMap(map);
    }

    /**
     * 统计一段时间的营业额
     * @param beginTime
     * @param endTime
     * @return
     */
    private Double getTurnOver(LocalDateTime beginTime,LocalDateTime endTime){
        Map map = new HashMap();
        map.put("status", Orders.COMPLETED);
        map.put("begin",beginTime);
        map.put("end", endTime);
        Double turnover = orderMapper.sumByMap(map);
        turnover = turnover == null ? 0.0 : turnover;
        return turnover;
    }
}
