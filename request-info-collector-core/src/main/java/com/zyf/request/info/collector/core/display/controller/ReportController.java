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

    @RequestMapping("/url")
    public Resp urlReport(String url, String category){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.countFiledByUrl(url,category));
    }
    @RequestMapping("/field/url")
    public Resp fieldUrlReport(String field,String value){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.countUrl(field,value));
    }
    @RequestMapping("/field/field")
    public Resp fieldFieldReport(String field,String value,String targetField){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.countFiledByField(field,value,targetField));
    }
}
