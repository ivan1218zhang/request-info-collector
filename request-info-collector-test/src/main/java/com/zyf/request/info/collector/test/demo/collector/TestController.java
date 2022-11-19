package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.Collected;
import com.zyf.request.info.collector.core.collector.annotation.Collector;
import com.zyf.request.info.collector.core.collector.annotation.CollectorTheme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IvanZhang
 */
@RestController
@CollectorTheme("test")
public class TestController {
    @Collector(ip = true,startTime = true,endTime = true,categories = {"oneUserId","name"}, isSuccess = true)
    @RequestMapping("/test")
    String test(TestReqVO reqVO,@Collected(fieldName = "test123") String test){
        return "123";
    }

    @Collector
    @RequestMapping("/test1")
    String test1(String name){
        return "test1";
    }
}
