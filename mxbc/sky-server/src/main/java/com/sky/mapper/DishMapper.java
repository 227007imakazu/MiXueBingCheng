package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/6 20:27
 * @注释
 */

/**
 * 菜品的持久层
 */
@Mapper
public interface DishMapper {

    /**
     * 根据分类的id查询其是否有关联的菜品(数量)
     * @param id
     * @return
     */
    @Select("select count(id) from dish where category_id=#{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据主键删除菜品数据
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id集合批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改菜品信息
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 动态条件查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 根据套餐id查询其菜品
     * @param setmealId
     * @return
     */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 根据状态查询菜品数量
     * @param status
     * @return
     */
    @Select("select count(id) from dish where status=#{status}")
    Integer countByStatus(Integer status);
}
