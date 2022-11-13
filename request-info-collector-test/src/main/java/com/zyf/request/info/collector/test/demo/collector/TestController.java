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
    @Collector(ip = true,startTime = true,endTime = true)
    @RequestMapping("/test")
    String test(TestReqVO reqVO,@Collected(fieldName = "test123") String test){
        return "123";
    }

    @RequestMapping("/test1")
    String test1(String name){
        return "test1";
    }
}
