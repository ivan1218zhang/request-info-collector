package com.zyf.request.info.collector.core.display.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangyf45
 * @date 2022/11/26 21:58
 */
public class SearchFieldByUrlCacheRespVO {
    private Set<String> fields;
    private Set<String> urls;

    public SearchFieldByUrlCacheRespVO() {
        fields=new HashSet<>();
        urls=new HashSet<>();
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "SearchFieldByUrlCacheRespVO{" +
                "fields=" + fields +
                ", urls=" + urls +
                '}';
    }
}
