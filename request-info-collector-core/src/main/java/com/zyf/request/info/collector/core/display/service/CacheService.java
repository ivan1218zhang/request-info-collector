package com.zyf.request.info.collector.core.display.service;

import com.zyf.request.info.collector.core.display.vo.SearchFieldByFieldCacheRespVO;
import com.zyf.request.info.collector.core.display.vo.SearchFieldByUrlCacheRespVO;
import com.zyf.request.info.collector.core.display.vo.SearchUrlByFieldCacheRespVO;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author IvanZhang
 */
@Service
public class CacheService {
    private Set<String> urls;
    private Set<String> fields;
    private Set<String> fieldValues;

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

    public SearchFieldByUrlCacheRespVO getSearchFieldByUrlCacheRespVO() {
        SearchFieldByUrlCacheRespVO vo=new SearchFieldByUrlCacheRespVO();
        vo.setUrls(urls);
        vo.setFields(fields);
        return vo;
    }

    public SearchUrlByFieldCacheRespVO getSearchUrlByFieldCacheRespVO() {
        SearchUrlByFieldCacheRespVO vo = new SearchUrlByFieldCacheRespVO();
        vo.setFieldValues(fieldValues);
        vo.setFields(fields);
        return vo;
    }

    public SearchFieldByFieldCacheRespVO getSearchFieldByFieldCacheRespVO() {
        SearchFieldByFieldCacheRespVO vo = new SearchFieldByFieldCacheRespVO();
        vo.setFields(fields);
        vo.setFiledValues(fieldValues);
        vo.setTargetFields(fields);
        return vo;
    }
}
