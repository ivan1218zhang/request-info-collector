package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.vo.CollectedVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyf45
 * @date 2022/11/29 00:14
 */
public class User implements CollectedVO {
    String name;

    @Override
    public Map<String, String> getCollectedMap() {
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        return map;
    }
}
