package com.zyf.request.info.collector.core.display.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IvanZhang
 */
public class SearchFieldByFieldCacheRespVO {
    Set<String> fields;
    Set<String> filedValues;
    Set<String> targetFields;

    public SearchFieldByFieldCacheRespVO() {
        fields = new HashSet<>();
        filedValues = new HashSet<>();
        targetFields = new HashSet<>();
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }

    public Set<String> getFiledValues() {
        return filedValues;
    }

    public void setFiledValues(Set<String> filedValues) {
        this.filedValues = filedValues;
    }

    public Set<String> getTargetFields() {
        return targetFields;
    }

    public void setTargetFields(Set<String> targetFields) {
        this.targetFields = targetFields;
    }

    @Override
    public String toString() {
        return "SearchFieldByFieldCacheRespVO{" +
                "fields=" + fields +
                ", values=" + filedValues +
                ", targetFields=" + targetFields +
                '}';
    }
}
