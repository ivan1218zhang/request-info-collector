package com.zyf.request.info.collector.core.collector.config;

import com.zyf.request.info.collector.core.collector.aspect.CollectorAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author IvanZhang
 */
@Configuration
public class CollectorConfig {
    /**
     * 对controller的接口切面编程
     */
    @Bean
    public CollectorAspect collector(){
        return new CollectorAspect();
    }
}
