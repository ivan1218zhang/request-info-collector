package com.zyf.request.info.collector.core.common.config;

import com.zyf.request.info.collector.core.display.cache.CacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author IvanZhang
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
        return cacheConfig;
    }
}
