package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/12 15:11
 * @注释
 */
@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入订单明细数据
     * @param orderDetails
     */
    void insertBatch(List<OrderDetail> orderDetails);

    /**
     * 根据订单id获取订单详细
     * @param orderId
     * @return
     */
    @Select("select * from order_detail where order_id=#{orderId}")
    List<OrderDetail> getByOrderId(Long orderId);
}
