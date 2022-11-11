package com.zyf.request.info.collector.core.collector.util;

import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.common.util.AOPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @author IvanZhang
 */
public class CollectorUtil {
    public static Collector getCollectorAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        Method method=AOPUtil.getMethod(proceedingJoinPoint);
        return AnnotationUtils.findAnnotation(method,Collector.class);
    }
}
