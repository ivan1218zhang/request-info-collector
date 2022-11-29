package com.zyf.request.info.collector.core.display.controller;

import com.zyf.request.info.collector.core.common.constant.RespConstants;
import com.zyf.request.info.collector.core.display.service.RequestInfoCollectorService;
import com.zyf.request.info.collector.core.common.vo.Resp;
import com.zyf.request.info.collector.core.display.vo.ReportReqVO;
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
    public Resp searchUrlByFieldReport(ReportReqVO requestVO){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.searchUrlByFieldReport(requestVO));
    }
    @RequestMapping("/field/url")
    public Resp searchFieldValueByUrlReport(ReportReqVO requestVO){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.searchFieldValueByUrlReport(requestVO));
    }
    @RequestMapping("/field/field")
    public Resp searchFieldByFieldReport(ReportReqVO requestVO){
        return new Resp(RespConstants.SUCCESS,requestInfoCollectorService.searchFieldByFieldReport(requestVO));
    }
}
