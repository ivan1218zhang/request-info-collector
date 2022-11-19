package com.zyf.request.info.collector.core.display.service;

import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.display.vo.ApiCategoryReportRespVO;
import com.zyf.request.info.collector.core.display.vo.ApiContextRespVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author IvanZhang
 */

@Service
public class RequestInfoCollectorService {
    private Map<String,List<ApiContextRespVO>> apiCache;

    @Resource
    private RedisClient redisClient;

    public void setApiCache(Map<String,List<ApiContextRespVO>> apiCache) {
        this.apiCache = apiCache;
    }

    public Map<String,List<ApiContextRespVO>> getApiCache(){
        return apiCache;
    }

    public ApiCategoryReportRespVO countFiled(String url,String category){
        ApiCategoryReportRespVO respVO=new ApiCategoryReportRespVO();
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Set<String> keys=redisTemplate.keys("REQUEST:"+url+"*");
        Map<String,Integer> res=new HashMap<>();
        if (keys==null){
            respVO.setTotal(0);
            return respVO;
        }
        respVO.setTotal(keys.size());
        for (String key:keys){
            String value= (String) redisTemplate.opsForHash().get(key,category);
            if (value==null){
                continue;
            }
            res.put(value,res.getOrDefault(value,0)+1);
        }
        respVO.setReport(res);
        return respVO;
    }
}
