package com.zyf.request.info.collector.core.display.vo;

import java.util.List;

/**
 * @author IvanZhang
 */
public class ReportReqVO {
    List<String> fieldNames;
    List<String> fieldValues;
    String targetField;
    String url;

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
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
                "fields=" + fieldNames+
                ", fieldValues=" + fieldValues +
                ", targetField='" + targetField + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
