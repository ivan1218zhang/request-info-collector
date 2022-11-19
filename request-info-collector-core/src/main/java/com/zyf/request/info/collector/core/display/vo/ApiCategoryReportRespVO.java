package com.zyf.request.info.collector.core.display.vo;

import java.util.Map;

/**
 * @author IvanZhang
 */
public class ApiCategoryReportRespVO {
    Integer total;
    Map<String,Integer> report;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Integer> getReport() {
        return report;
    }

    public void setReport(Map<String, Integer> data) {
        this.report = data;
    }
}
