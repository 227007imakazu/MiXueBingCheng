package com.sky.controller.user;

import com.alibaba.fastjson.JSON;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.OrderDetail;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ResolveResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/12 15:01
 * @订单管理
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "C端-订单管理接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单：{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     * 订单详细信息
     * @param id
     * @return
     */
    // TODO 开发订单详细页
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("订单详细信息")
    public Result<OrderVO> orderDetail(@PathVariable("id") Long id){
        OrderVO orderVO=orderService.detail(id);
        return Result.success(orderVO);
    }


    /**
     * 历史订单列表
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO){
        PageResult pageResult=orderService.pageQuery(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id催单
     * @param id
     * @return
     */
    @GetMapping("/reminder/{id}")
    @ApiOperation("催单")
    public Result reminder(@PathVariable("id") Long id){
        log.info("该用户向商家催单:{}",id);
        orderService.reminder(id);
        return Result.success();
    }

    /**
     * 再来一单
     * @param id
     * @return
     */
    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public Result repetition(@PathVariable("id") Long id){
        orderService.repetition(id);
        return Result.success();
    }

    /**
     * 拒单
     * @param id
     * @return
     * @throws Exception
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation("拒单")
    public Result cancel(@PathVariable("id") Long id)throws Exception{
        orderService.cancelOrder(id);
        return Result.success();
    }




}
