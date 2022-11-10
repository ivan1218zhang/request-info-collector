package com.zyf.request.info.collector.core.collector.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author IvanZhang
 */
@Aspect
public class Collector {
    @Around("@annotation(com.zyf.request.info.collector.core.collector.annotation.Collector)")
    public Object printInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //request信息获取
        //正常流程执行
        Object res = proceedingJoinPoint.proceed();
        //执行成功去存数据
        return res;
    }
}
