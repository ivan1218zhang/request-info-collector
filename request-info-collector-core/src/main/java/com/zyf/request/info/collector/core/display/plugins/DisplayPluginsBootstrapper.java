package com.zyf.request.info.collector.core.display.plugins;

import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.collector.annotation.CollectorTheme;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import com.zyf.request.info.collector.core.display.vo.ApiContextRespVO;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author IvanZhang
 */
@Component
public class DisplayPluginsBootstrapper implements SmartLifecycle {
    @Resource
    ApplicationContext applicationContext;

    @Resource
    RequestInfoCollectorService requestInfoCollectorService;

    @Override
    public void start() {
        //TODO 生成apiCache
        Map<String,List<ApiContextRespVO>> apiCache=new HashMap<>();
        Map<String,Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);
        for (String key:controllers.keySet()){
            Class controller= AopUtils.getTargetClass(controllers.get(key));
            Method[] methods=controller.getDeclaredMethods();
            RequestMapping topRequestMapping = (RequestMapping) controller.getAnnotation(RequestMapping.class);
            String baseUrl=topRequestMapping==null?"":topRequestMapping.value()[0];
            CollectorTheme collectorTheme = (CollectorTheme) controller.getAnnotation(CollectorTheme.class);
            String theme = collectorTheme==null?"default": collectorTheme.value();
            for (Method method:methods){
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping==null){
                    continue;
                }
                Collector collector = method.getAnnotation(Collector.class);
                if (collector==null){
                    continue;
                }
                List<ApiContextRespVO> apiContextRespVos = apiCache.computeIfAbsent(theme, k -> new ArrayList<>());
                String url = baseUrl+requestMapping.value()[0];
                ApiContextRespVO apiContextRespVO =new ApiContextRespVO();
                apiContextRespVO.setUrl(url);
                apiContextRespVO.setCategories(collector.categories());
                apiContextRespVos.add(apiContextRespVO);
            }
        }
        requestInfoCollectorService.setApiCache(apiCache);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
