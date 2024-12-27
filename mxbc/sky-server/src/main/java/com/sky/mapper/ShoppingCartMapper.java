package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/11 14:23
 * @注释
 */
@Mapper
public interface ShoppingCartMapper {
    /**
     * 条件查询
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 更新商品数量
     *
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     *
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            " values (#{name},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空指定用户的购物车
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void deleteByUserId(Long userId);


    /**
     * 根据商品id删除购物车记录
     * @param id
     */
    @Delete("delete from shopping_cart where id=#{id}")
    void deleteById(Long id);

    /**
     * 批量插入购物车记录
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
