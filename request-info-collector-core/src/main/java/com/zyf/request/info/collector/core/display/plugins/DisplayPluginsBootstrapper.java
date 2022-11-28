package com.zyf.request.info.collector.core.display.plugins;

import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.display.cache.CacheConfig;
import com.zyf.request.info.collector.core.display.service.CacheService;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author IvanZhang
 */
@Component
public class DisplayPluginsBootstrapper implements SmartLifecycle {
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private CacheService cacheService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private CacheConfig cacheConfig;

    @Override
    public void start() {
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Map<String,Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);
        for (String key:controllers.keySet()){
            Class<?> controller= AopUtils.getTargetClass(controllers.get(key));
            Method[] methods=controller.getDeclaredMethods();
            RequestMapping topRequestMapping = controller.getAnnotation(RequestMapping.class);
            String baseUrl=topRequestMapping==null?"":topRequestMapping.value()[0];
            for (Method method:methods){
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping==null){
                    continue;
                }
                Collector collector = method.getAnnotation(Collector.class);
                if (collector==null){
                    continue;
                }
                String url = baseUrl+requestMapping.value()[0];
                if (cacheConfig.getDefaultUrls()){
                    cacheConfig.getUrls().add(url);
                }
                if (cacheConfig.getDefaultFields()||cacheConfig.getDefaultFieldValues()){
                    //从redis获取field
                    Set<String> keys=redisTemplate.keys("REQUEST:"+url+":*");
                    if (keys==null){
                        continue;
                    }
                    for (String k:keys){
                        Map<Object,Object> entries=redisTemplate.opsForHash().entries(k);
                        if (cacheConfig.getDefaultFields()){
                            for (Object field:entries.keySet()){
                                cacheConfig.getFields().add((String) field);
                            }
                        }
                        if (cacheConfig.getDefaultFieldValues()){
                            for (Object field:entries.values()){
                                cacheConfig.getFieldValues().add((String) field);
                            }
                        }
                    }
                }
            }
        }
        cacheService.setUrls(cacheConfig.getUrls());
        cacheService.setFields(cacheConfig.getFields());
        cacheService.setFieldValues(cacheConfig.getFieldValues());
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
