package com.zyf.request.info.collector.core.common.selector;

import com.zyf.request.info.collector.core.collector.config.Config;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author IvanZhang
 */
public class RequestInfoCollectorSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Config.class.getName()};
    }
}
