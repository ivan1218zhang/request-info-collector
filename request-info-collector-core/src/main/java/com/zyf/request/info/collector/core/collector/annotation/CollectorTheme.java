package com.zyf.request.info.collector.core.collector.annotation;

import java.lang.annotation.*;

/**
 * @author IvanZhang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CollectorTheme {
    String value() default "";
}
