package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.common.redis.RedisConfig;
import com.zyf.request.info.collector.core.display.cache.CacheConfig;
import com.zyf.request.info.collector.core.display.vo.SearchFieldByUrlCacheRespVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IvanZhang
 */
@Configuration
public class TestConfig {
    @Bean
    public RedisClient test(){
        RedisConfig redisConfig=new RedisConfig();
        redisConfig.setAddress("localhost");
        redisConfig.setPort(6379);
        redisConfig.setPassword("");
        return RedisClient.create(redisConfig);
    }
    @Bean
    public CacheConfig cacheConfig(){
        CacheConfig cacheConfig=new CacheConfig();
        cacheConfig.setDefaultUrls(true);
        cacheConfig.setDefaultFields(true);
        cacheConfig.setDefaultFieldValues(true);
        return cacheConfig;
    }
}
