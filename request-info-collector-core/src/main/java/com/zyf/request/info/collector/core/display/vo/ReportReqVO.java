package com.zyf.request.info.collector.core.display.vo;

import java.util.List;

/**
 * @author IvanZhang
 */
public class ReportReqVO {
    List<String> fields;
    List<String> fieldValues;
    String targetField;
    String url;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    @Override
    public String toString() {
        return "ReportReqVO{" +
                "fields=" + fields +
                ", fieldValues=" + fieldValues +
                ", targetField='" + targetField + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
