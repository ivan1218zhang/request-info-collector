package com.zyf.request.info.collector.core.common.config;

import com.zyf.request.info.collector.core.display.cache.CacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

/**
 * @author zhangyf45
 * @date 2022/11/27 00:21
 */
@Configuration
public class BeanConfig {
    @Bean
    @ConditionalOnMissingBean
    CacheConfig cacheConfig(){
        CacheConfig cacheConfig=new CacheConfig();
        cacheConfig.setDefaultFields(false);
        cacheConfig.setDefaultFieldValues(false);
        cacheConfig.setDefaultUrls(false);
        cacheConfig.setUrls(new HashSet<>());
        cacheConfig.setFields(new HashSet<>());
        cacheConfig.setFieldValues(new HashSet<>());
        return cacheConfig;
    }
}
