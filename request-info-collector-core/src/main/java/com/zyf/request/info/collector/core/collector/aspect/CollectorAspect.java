package com.zyf.request.info.collector.core.collector.aspect;

import com.zyf.request.info.collector.core.collector.annotation.Collected;
import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.collector.exception.FiledNameException;
import com.zyf.request.info.collector.core.collector.vo.CollectedVO;
import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.common.util.IdGenerator;
import com.zyf.request.info.collector.core.common.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author IvanZhang
 */
@Aspect
public class CollectorAspect {
    @Resource
    private RedisClient redisClient;
    @Resource
    private HttpServletRequest request;

    @Around("@annotation(com.zyf.request.info.collector.core.collector.annotation.Collector)")
    public Object printInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Map<String,String> data=new HashMap<>();
        //获取方法上的注解
        Collector collector = method.getAnnotationsByType(Collector.class)[0];
        //获取请求uri
        String uri=request.getRequestURI();
        //生成唯一id
        String id=IdGenerator.getIdStr();
        data.put("requestId",id);
        //生成key
        String key="REQUEST:"+uri+":"+id;
        //获取参数
        Object[] params = proceedingJoinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        //合并要收集的值
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Parameter parameter = parameters[i];
            if (param instanceof CollectedVO){
                Map<String,String> hashMap = ((CollectedVO) param).getCollectedMap();
                for(String k:hashMap.keySet()){
                    String v = hashMap.get(k);
                    if (v==null){
                        v="";
                    }
                    if (data.put(k,v)!=null){
                        throw new FiledNameException(k);
                    }
                }
                continue;
            }
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if(param == null || paramAnn.length == 0){
                continue;
            }
            for (Annotation annotation : paramAnn) {
                if(annotation.annotationType().equals(Collected.class)){
                    String fieldName=parameter.getAnnotation(Collected.class).fieldName();
                    if ("".equals(fieldName)){
                        data.put(parameter.getName(),param.toString());
                    }else {
                        data.put(fieldName,param.toString());
                    }
                    break;
                }
            }
        }
        //获取ip地址
        if (collector.ip()){
            String ip= RequestUtil.getIp(request);
            data.put("ip",ip);
        }
        //获取请求开始时间
        if (collector.startTime()){
            Long start = System.currentTimeMillis();
            data.put("startTime", String.valueOf(start));
        }
        //执行
        Object res;
        try{
             res = proceedingJoinPoint.proceed();
        }catch (Exception e){
            //获取请求结束时间
            if (collector.endTime()){
                Long end = System.currentTimeMillis();
                data.put("endTime",String.valueOf(end));
            }
            //请求失败
            if (collector.isSuccess()){
                data.put("isSuccess","false");
            }
            //执行失败去存数据
            redisClient.put(key,data);
            throw e;
        }
        //获取请求结束时间
        if (collector.endTime()){
            Long end = System.currentTimeMillis();
            data.put("endTime",String.valueOf(end));
        }
        //请求失败
        if (collector.isSuccess()){
            data.put("isSuccess","false");
        }
        //请求失败
        if (collector.isSuccess()){
            data.put("isSuccess","true");
        }
        //执行成功去存数据
        redisClient.put(key,data);
        return res;
    }
}
