package com.zyf.request.info.collector.core.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author IvanZhang
 */
public class AOPUtil {
    /**
     * 从proceedingJoinPoint中获取aop当前执行的方法
     * @param proceedingJoinPoint
     * @return
     */
    public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature=proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature methodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        return  methodSignature.getMethod();
    }
}
