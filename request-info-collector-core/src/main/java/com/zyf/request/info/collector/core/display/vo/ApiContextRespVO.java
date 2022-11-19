package com.zyf.request.info.collector.core.display.vo;

/**
 * @author IvanZhang
 */
public class ApiContextRespVO {
    String url;
    String[] categories;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
