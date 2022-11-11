package com.zyf.request.info.collector.core.collector.annotation;

import com.zyf.request.info.collector.core.common.selector.RequestInfoCollectorSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author IvanZhang
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(RequestInfoCollectorSelector.class)
public @interface EnableRequestInfoCollector {
}
