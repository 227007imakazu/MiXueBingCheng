package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/9 15:21
 * @注释
 */
@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("开始创建redis模板对象");
        RedisTemplate redisTemplate = new RedisTemplate();
        //设置连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;


    }
}
