package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.Collected;
import com.zyf.request.info.collector.core.collector.annotation.Collector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IvanZhang
 */
@RestController
public class TestController {
    @Collector(ip = true, isSuccess = true)
    @RequestMapping("/test1")
    String test1(TestReqVO reqVO){
        return "test1:"+reqVO.getName();
    }

    @Collector(ip = true, isSuccess = true)
    @RequestMapping("/test2")
    String test2(@Collected(fieldName = "name") String name){
        return "test2:"+name;
    }

    @Collector(ip = true, isSuccess = true)
    @RequestMapping("/test3")
    String test3(@Collected(fieldName = "name") String name){
        return "test3:"+name;
    }
}
