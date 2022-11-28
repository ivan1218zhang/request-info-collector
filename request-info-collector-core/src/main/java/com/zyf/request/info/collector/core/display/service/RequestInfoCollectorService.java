package com.zyf.request.info.collector.core.display.service;

import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.display.vo.ReportRespVO;
import com.zyf.request.info.collector.core.utils.ReportUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author IvanZhang
 */

public class RequestInfoCollectorService {

    @Resource
    private RedisClient redisClient;

    public ReportRespVO searchUrlByField(String field, String value) {
        ReportRespVO respVO=new ReportRespVO();
        if (value==null){
            respVO.setTotal(0);
            return respVO;
        }
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Set<String> keys=redisTemplate.keys("REQUEST:"+"*");
        Map<String,Integer> res=new HashMap<>();
        if (keys==null){
            respVO.setTotal(0);
            return respVO;
        }
        Integer count=0;
        for (String key:keys){
            String v= (String) redisTemplate.opsForHash().get(key,field);
            if (!value.equals(v)){
                continue;
            }
            count++;
            String[] keyParts=key.split(":");
            if (keyParts.length!=3){
                continue;
            }
            res.put(keyParts[1],res.getOrDefault(keyParts[1],0)+1);
        }
        respVO.setTotal(count);
        ArrayList<String> labels=new ArrayList<>(res.keySet());
        ArrayList<Integer> values=new ArrayList<>(res.values());
        ReportUtils.sort(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            respVO.setLabels(labels.subList(0,10));
            respVO.setValues(values.subList(0,10));
        }else {
            respVO.setLabels(labels);
            respVO.setValues(values);
        }
        return respVO;
    }

    public ReportRespVO countFiledByField(String field, String value, String targetField) {
        ReportRespVO respVO=new ReportRespVO();
        if (value==null){
            respVO.setTotal(0);
            return respVO;
        }
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Set<String> keys=redisTemplate.keys("REQUEST:"+"*");
        Map<String,Integer> res=new HashMap<>();
        if (keys==null){
            respVO.setTotal(0);
            return respVO;
        }
        Integer count=0;
        for (String key:keys){
            String v= (String) redisTemplate.opsForHash().get(key,field);
            if (!value.equals(v)){
                continue;
            }
            count++;
            String targetValue=(String) redisTemplate.opsForHash().get(key,targetField);
            if (targetValue==null){
                continue;
            }
            res.put(targetValue,res.getOrDefault(targetValue,0)+1);
        }
        respVO.setTotal(count);
        ArrayList<String> labels=new ArrayList<>(res.keySet());
        ArrayList<Integer> values=new ArrayList<>(res.values());
        ReportUtils.sort(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            respVO.setLabels(labels.subList(0,10));
            respVO.setValues(values.subList(0,10));
        }else {
            respVO.setLabels(labels);
            respVO.setValues(values);
        }
        return respVO;
    }

    public ReportRespVO searchFieldByUrlReport(String field, String url) {
        ReportRespVO respVO=new ReportRespVO();
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Set<String> keys=redisTemplate.keys("REQUEST:"+url+":*");
        Map<String,Integer> res=new HashMap<>();
        if (keys==null){
            respVO.setTotal(0);
            return respVO;
        }
        respVO.setTotal(keys.size());
        for (String key:keys){
            String value= (String) redisTemplate.opsForHash().get(key,field);
            if (value==null){
                continue;
            }
            res.put(value,res.getOrDefault(value,0)+1);
        }
        ArrayList<String> labels=new ArrayList<>(res.keySet());
        ArrayList<Integer> values=new ArrayList<>(res.values());
        ReportUtils.sort(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            respVO.setLabels(labels.subList(0,10));
            respVO.setValues(values.subList(0,10));
        }else {
            respVO.setLabels(labels);
            respVO.setValues(values);
        }
        return respVO;
    }
}
