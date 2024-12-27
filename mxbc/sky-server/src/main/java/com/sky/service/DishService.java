package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/7 15:42
 * @注释
 */
public interface DishService {
    /**
     * 保存菜品和口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品和口味
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 根据id修改菜品
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 起售停售菜品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
