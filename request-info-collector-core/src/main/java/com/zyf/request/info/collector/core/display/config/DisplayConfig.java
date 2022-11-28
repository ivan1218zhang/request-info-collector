package com.zyf.request.info.collector.core.display.config;

import com.zyf.request.info.collector.core.display.plugins.DisplayPluginsBootstrapper;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author IvanZhang
 */
@Configuration
public class DisplayConfig {
    @Bean
    RequestInfoCollectorService requestInfoCollectorService(){
        return new RequestInfoCollectorService();
    }
    @ConditionalOnMissingBean
    DisplayPluginsBootstrapper displayPluginsBootstrapper(){
        return new DisplayPluginsBootstrapper();
    }

}
