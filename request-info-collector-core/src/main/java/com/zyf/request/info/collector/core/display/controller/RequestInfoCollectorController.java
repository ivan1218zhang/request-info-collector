package com.zyf.request.info.collector.core.display.controller;

import com.zyf.request.info.collector.core.display.constant.RespConstant;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import com.zyf.request.info.collector.core.display.vo.Resp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author IvanZhang
 */
@RestController
@RequestMapping("/request/info/collector")
public class RequestInfoCollectorController {
    @Resource
    private RequestInfoCollectorService requestInfoCollectorService;

    @RequestMapping("/api/cache")
    public Resp apiCache(){
        return new Resp(RespConstant.SUCCESS,requestInfoCollectorService.getApiCache());
    }
    @RequestMapping("/report/url")
    public Resp urlReport(String url, String category){
        return new Resp(RespConstant.SUCCESS,requestInfoCollectorService.countFiled(url,category));
    }
    @RequestMapping("/report/field")
    public Resp fieldReport(String field,String value){
        return new Resp(RespConstant.SUCCESS,requestInfoCollectorService.countUrl(field,value));
    }
}
