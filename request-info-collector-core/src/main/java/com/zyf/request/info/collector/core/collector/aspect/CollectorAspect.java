package com.zyf.request.info.collector.core.collector.aspect;

import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.collector.util.CollectorUtil;
import com.zyf.request.info.collector.core.common.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author IvanZhang
 */
@Aspect
public class CollectorAspect {
    @Resource
    private HttpServletRequest request;

    @Around("@annotation(com.zyf.request.info.collector.core.collector.annotation.Collector)")
    public Object printInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        //筛选参数
        Map<String, List<String>> target = new HashMap<>();
        //获取请求开始时间
        Long start = System.currentTimeMillis();
        //获取ip地址
        String ip= RequestUtil.getIP(request);
        //执行
        Object res = proceedingJoinPoint.proceed();
        //获取请求结束时间
        Long end = System.currentTimeMillis();
        //执行成功去存数据
        return res;
    }
}
