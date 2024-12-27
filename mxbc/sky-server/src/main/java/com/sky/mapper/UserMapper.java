package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/10 21:03
 * @注释
 */
@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户是否存在
     * @param openid
     * @return
     */
    @Select("select* from user where openid=#{openid}")
    User getByOpenid(String openid);

    /**
     * 新增用户
     * @param user
     */
    void insert(User user);


    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select* from user where id=#{userId}")
    User getById(Long userId);

    /**
     * 动态统计人数
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
