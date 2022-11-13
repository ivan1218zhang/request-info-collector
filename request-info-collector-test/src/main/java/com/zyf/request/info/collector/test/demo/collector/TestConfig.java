package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.common.redis.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author IvanZhang
 */
@Configuration
public class TestConfig {
    @Bean
    public RedisClient test(){
        RedisConfig redisConfig=new RedisConfig();
        redisConfig.setAddress("192.168.233.123");
        redisConfig.setPort(6379);
        redisConfig.setPassword("3333");
        return RedisClient.create(redisConfig);
    }
}
