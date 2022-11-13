package com.zyf.request.info.collector.core.collector.annotation;

import java.lang.annotation.*;

/**
 * @author IvanZhang
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Collected {
    String fieldName() default "";
}
