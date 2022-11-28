package com.zyf.request.info.collector.core.display.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IvanZhang
 */
public class CacheConfig {
    Boolean defaultUrls;
    Boolean defaultFields;
    Boolean defaultFieldValues;
    Set<String> urls;
    Set<String> fields;
    Set<String> fieldValues;

    public CacheConfig() {
        urls=new HashSet<>();
        fields=new HashSet<>();
        fieldValues=new HashSet<>();
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
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

    public Boolean getDefaultUrls() {
        return defaultUrls;
    }

    public void setDefaultUrls(Boolean defaultUrls) {
        this.defaultUrls = defaultUrls;
    }

    public Boolean getDefaultFields() {
        return defaultFields;
    }

    public void setDefaultFields(Boolean defaultFields) {
        this.defaultFields = defaultFields;
    }

    public Boolean getDefaultFieldValues() {
        return defaultFieldValues;
    }

    public void setDefaultFieldValues(Boolean defaultFieldValues) {
        this.defaultFieldValues = defaultFieldValues;
    }
}
