package com.zyf.request.info.collector.core.config;

import com.zyf.request.info.collector.core.aspect.Collector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author IvanZhang
 */
@Configuration
@EnableAspectJAutoProxy
public class SpringConfig {
    @Bean
    public Collector collector(){
        return new Collector();
    }
}
