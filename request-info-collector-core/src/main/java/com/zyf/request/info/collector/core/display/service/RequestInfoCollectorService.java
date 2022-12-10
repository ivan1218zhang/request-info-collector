package com.zyf.request.info.collector.core.display.service;

import com.zyf.request.info.collector.core.common.redis.RedisClient;
import com.zyf.request.info.collector.core.display.vo.ReportReqVO;
import com.zyf.request.info.collector.core.display.vo.ReportRespVO;
import com.zyf.request.info.collector.core.utils.ReportUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author IvanZhang
 */

public class RequestInfoCollectorService {

    @Resource
    private RedisClient redisClient;

    public ReportRespVO searchUrlByFieldReport(ReportReqVO requestVO) {
        ReportRespVO respVO=new ReportRespVO();
        if (requestVO.getFieldNames()==null||requestVO.getFieldValues()==null){
            respVO.setTotal(0);
            return respVO;
        }
        if (requestVO.getFieldNames().size()!=requestVO.getFieldValues().size()){
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
            Iterator<String> fieldIterator=requestVO.getFieldNames().iterator();
            Iterator<String> valueIterator=requestVO.getFieldValues().iterator();
            boolean flag = false;
            while (fieldIterator.hasNext()&& valueIterator.hasNext()){
                String field = fieldIterator.next();
                String value = valueIterator.next();
                String v= (String) redisTemplate.opsForHash().get(key,field);
                if (!value.equals(v)){
                    flag=true;
                    break;
                }
            }
            if (flag){
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
        ReportUtils.sortDesc(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            labels = new ArrayList<>(labels.subList(0,10));
            values = new ArrayList<>(values.subList(0,10));
        }
        ReportUtils.sortAsc(labels,values);
        respVO.setValues(values);
        respVO.setLabels(labels);
        return respVO;
    }

    public ReportRespVO searchFieldByFieldReport(ReportReqVO requestVO) {
        ReportRespVO respVO=new ReportRespVO();
        if (requestVO.getFieldNames()==null||requestVO.getFieldValues()==null||requestVO.getTargetField()==null){
            respVO.setTotal(0);
            return respVO;
        }
        if (requestVO.getFieldNames().size()!=requestVO.getFieldValues().size()){
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
            Iterator<String> fieldIterator=requestVO.getFieldNames().iterator();
            Iterator<String> valueIterator=requestVO.getFieldValues().iterator();
            Boolean flag = false;
            while (fieldIterator.hasNext()&& valueIterator.hasNext()){
                String field = fieldIterator.next();
                String value = valueIterator.next();
                String v= (String) redisTemplate.opsForHash().get(key,field);
                if (!value.equals(v)){
                    flag=true;
                    break;
                }
            }
            if (flag){
                continue;
            }
            count++;
            String targetValue=(String) redisTemplate.opsForHash().get(key,requestVO.getTargetField());
            if (targetValue==null){
                continue;
            }
            res.put(targetValue,res.getOrDefault(targetValue,0)+1);
        }
        respVO.setTotal(count);
        ArrayList<String> labels=new ArrayList<>(res.keySet());
        ArrayList<Integer> values=new ArrayList<>(res.values());
        ReportUtils.sortDesc(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            labels = new ArrayList<>(labels.subList(0,10));
            values = new ArrayList<>(values.subList(0,10));
        }
        ReportUtils.sortAsc(labels,values);
        respVO.setValues(values);
        respVO.setLabels(labels);
        return respVO;
    }

    public ReportRespVO searchFieldValueByUrlReport(ReportReqVO requestVO) {
        ReportRespVO respVO=new ReportRespVO();
        RedisTemplate<String,String> redisTemplate=redisClient.getRedisTemplate();
        Set<String> keys=redisTemplate.keys("REQUEST:"+requestVO.getUrl()+":*");
        Map<String,Integer> res=new HashMap<>();
        if (keys==null){
            respVO.setTotal(0);
            return respVO;
        }
        respVO.setTotal(keys.size());
        for (String key:keys){
            String value= (String) redisTemplate.opsForHash().get(key,requestVO.getTargetField());
            if (value==null){
                continue;
            }
            res.put(value,res.getOrDefault(value,0)+1);
        }
        ArrayList<String> labels=new ArrayList<>(res.keySet());
        ArrayList<Integer> values=new ArrayList<>(res.values());
        ReportUtils.sortDesc(labels,values);
        //截取出现次数最多的前十个
        if (labels.size()>10){
            labels = new ArrayList<>(labels.subList(0,10));
            values = new ArrayList<>(values.subList(0,10));
        }
        ReportUtils.sortAsc(labels,values);
        respVO.setValues(values);
        respVO.setLabels(labels);
        return respVO;
    }
}
