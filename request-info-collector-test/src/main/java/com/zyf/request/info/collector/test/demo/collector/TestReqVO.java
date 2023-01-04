package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.vo.CollectedVO;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IvanZhang
 */
@Data
public class TestReqVO implements CollectedVO{
    private String name;
    private String id;

    @Override
    public Map<String, String> getCollectedMap() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("oneUserId",id);
        return hashMap;
    }
}
