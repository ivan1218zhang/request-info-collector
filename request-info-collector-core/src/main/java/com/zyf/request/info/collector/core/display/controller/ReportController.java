package com.zyf.request.info.collector.core.display.controller;

import com.zyf.request.info.collector.core.common.constant.RespConstants;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import com.zyf.request.info.collector.core.common.vo.Resp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author IvanZhang
 */
@RestController
@RequestMapping("/request/info/collector/report")
public class ReportController {
    @Resource
    private RequestInfoCollectorService requestInfoCollectorService;

    @RequestMapping("/url/field")
    public Resp searchUrlByField(String field, String fieldValue){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.searchUrlByField(field,fieldValue));
    }
    @RequestMapping("/field/url")
    public Resp searchFieldByUrlReport(String field,String url){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.searchFieldByUrlReport(field,url));
    }
    @RequestMapping("/field/field")
    public Resp fieldFieldReport(String field,String value,String targetField){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.countFiledByField(field,value,targetField));
    }
}
