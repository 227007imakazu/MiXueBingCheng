package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/16 13:48
 * @注释
 */
@RestController
@RequestMapping("/admin/workspace")
@Slf4j
@Api(tags = "工作台相关接口")
public class WorkSpaceController {
    @Autowired
    private WorkspaceService workspaceService;

    /**
     * 今日数据
     * @return
     */
    @GetMapping("/businessData")
    @ApiOperation("今日数据接口")
    public Result<BusinessDataVO> businessData(){
        //获得当天的开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天的结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);

        return Result.success(businessDataVO);
    }

    /**
     * 订单管理
     * @return
     */
    @GetMapping("/overviewOrders")
    @ApiOperation("订单管理接口")
    public Result<OrderOverViewVO> overviewOrders(){

        OrderOverViewVO orderOverViewVO=workspaceService.getOverviewOrders();

        return Result.success(orderOverViewVO);
    }
    /**
     * 菜品总览
     * @return
     */
    @GetMapping("/overviewDishes")
    @ApiOperation("菜品总览接口")
    public Result<DishOverViewVO> overviewDishes(){

        DishOverViewVO dishOverViewVO=workspaceService.getOverviewDishes();

        return Result.success(dishOverViewVO);
    }
    /**
     * 套餐总览
     * @return
     */
    @GetMapping("/overviewSetmeals")
    @ApiOperation("套餐总览接口")
    public Result<SetmealOverViewVO> overviewSetmeals(){

        SetmealOverViewVO setmealOverViewVO=workspaceService.getOverviewSetmeals();

        return Result.success(setmealOverViewVO);
    }
}
