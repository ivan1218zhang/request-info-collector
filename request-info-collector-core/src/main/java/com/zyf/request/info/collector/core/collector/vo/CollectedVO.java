package com.zyf.request.info.collector.core.collector.vo;

import java.util.Map;

/**
 * @author IvanZhang
 */
public interface CollectedVO {
    /**
     * 获取要收集的数据 让开发者自己去实现
     * @return
     */
    Map<String,String> getCollectedMap();
}
