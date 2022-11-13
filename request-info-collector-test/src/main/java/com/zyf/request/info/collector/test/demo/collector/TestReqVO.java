package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.vo.CollectedVO;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IvanZhang
 */
@Data
public class TestReqVO implements CollectedVO {
    String name;
    String id;

    @Override
    public Map<String, String> getCollectedMap() {
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("oneUserId",id);
        return map;
    }
}
