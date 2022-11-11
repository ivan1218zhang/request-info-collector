package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.Collected;
import lombok.Data;

/**
 * @author zhangyf45
 * @date 2022/11/11 00:28
 */
@Data
public class TestReqVO {
    @Collected
    String name;
    String id;
}
