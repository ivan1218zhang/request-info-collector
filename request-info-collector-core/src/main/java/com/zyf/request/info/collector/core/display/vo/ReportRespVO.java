package com.zyf.request.info.collector.core.display.vo;

import java.util.List;

/**
 * @author IvanZhang
 */
public class ReportRespVO {
    private Integer total;
    private List<String> labels;
    private List<Integer> values;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
