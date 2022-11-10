package com.zyf.request.info.collector.core.collector.annotation;

import java.lang.annotation.*;

/**
 * @author IvanZhang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Collector {
    String[] value() default {""};
}
