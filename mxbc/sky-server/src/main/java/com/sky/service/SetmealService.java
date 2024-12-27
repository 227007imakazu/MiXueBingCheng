package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/8 13:40
 * @注释
 */
public interface SetmealService {
    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 套餐批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐及关联菜品
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐和菜品
     * @param setmealDTO
     */
    void updateWithDish(SetmealDTO setmealDTO);

    /**
     * 起售停售套餐
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
