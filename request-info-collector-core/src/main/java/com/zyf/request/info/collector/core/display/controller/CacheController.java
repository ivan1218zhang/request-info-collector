package com.zyf.request.info.collector.core.display.controller;

import com.zyf.request.info.collector.core.common.constant.RespConstants;
import com.zyf.request.info.collector.core.common.vo.Resp;
import com.zyf.request.info.collector.core.display.service.CacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author IvanZhang
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
    @RequestMapping("/field/field")
    Resp searchFieldByFieldValueCache(){
        return new Resp(RespConstants.SUCCESS,cacheService.getSearchFieldByFieldCacheRespVO());
    }
    @RequestMapping("/url/field")
    Resp searchUrlByFieldValueCache(){
        return new Resp(RespConstants.SUCCESS,cacheService.getSearchUrlByFieldCacheRespVO());
    }
}
