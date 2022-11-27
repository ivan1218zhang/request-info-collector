package com.zyf.request.info.collector.core.display.controller;

import com.zyf.request.info.collector.core.common.constant.RespConstants;
import com.zyf.request.info.collector.core.common.vo.Resp;
import com.zyf.request.info.collector.core.display.service.CacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangyf45
 * @date 2022/11/26 21:45
 */
@RestController
@RequestMapping("/request/info/collector/cache")
public class CacheController {
    @Resource
    CacheService cacheService;

    @RequestMapping("/field/url")
    Resp searchFieldByUrlCache(){
        return new Resp(RespConstants.SUCCESS,cacheService.getSearchFieldByUrlCacheRespVO());
    }
    @RequestMapping("/field/filed")
    Resp searchFieldByFieldValueCache(){
        return new Resp(RespConstants.SUCCESS,cacheService.getSearchFieldByFieldCacheRespVO());
    }
    @RequestMapping("/url/filed")
    Resp searchUrlByFieldValueCache(){
        return new Resp(RespConstants.SUCCESS,cacheService.getSearchUrlByFieldCacheRespVO());
    }
}
