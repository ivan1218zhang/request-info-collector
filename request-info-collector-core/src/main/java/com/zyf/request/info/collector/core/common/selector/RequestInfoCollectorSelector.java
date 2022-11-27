package com.zyf.request.info.collector.core.common.selector;

import com.zyf.request.info.collector.core.collector.aspect.CollectorAspect;
import com.zyf.request.info.collector.core.common.config.BeanConfig;
import com.zyf.request.info.collector.core.display.controller.CacheController;
import com.zyf.request.info.collector.core.display.controller.ReportController;
import com.zyf.request.info.collector.core.display.plugins.DisplayPluginsBootstrapper;
import com.zyf.request.info.collector.core.display.service.CacheService;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author IvanZhang
 */
public class RequestInfoCollectorSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                BeanConfig.class.getName(),
                CollectorAspect.class.getName(),
                DisplayPluginsBootstrapper.class.getName(),
                RequestInfoCollectorService.class.getName(),
                ReportController.class.getName(),
                CacheService.class.getName(),
                CacheController.class.getName()
        };
    }
}
