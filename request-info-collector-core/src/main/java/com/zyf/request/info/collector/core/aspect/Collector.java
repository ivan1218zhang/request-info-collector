package com.zyf.request.info.collector.core.aspect;

import com.zyf.request.info.collector.core.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author IvanZhang
 */
@Aspect
public class Collector {
    @Around("@annotation(com.zyf.request.info.collector.core.annotation.Collector)")
    public Object printInfo(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("collector execute");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String ip= RequestUtil.getIp(request);
        System.out.println(ip);
        Object object;
        try {
            object=proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
