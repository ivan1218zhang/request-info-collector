package com.zyf.request.info.collector.core.display.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IvanZhang
 */
public class SearchUrlByFieldCacheRespVO {
    Set<String> fields;
    Set<String> fieldValues;

    public SearchUrlByFieldCacheRespVO() {
        fields = new HashSet<>();
        fieldValues = new HashSet<>();
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }

    public Set<String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public String toString() {
        return "SearchUrlByFieldCacheRespVO{" +
                "fields=" + fields +
                ", values=" + fieldValues +
                '}';
    }
}
