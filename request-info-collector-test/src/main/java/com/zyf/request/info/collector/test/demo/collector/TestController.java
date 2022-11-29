package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.Collected;
import com.zyf.request.info.collector.core.collector.annotation.Collector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IvanZhang
 */
@RestController
public class TestController {
    @Collector(startTime = true,endTime = true, isSuccess = true)
    @RequestMapping("/test")
    String test(@RequestBody TestReqVO reqVO){
        return "123";
    }

    @Collector(ip = true)
    @RequestMapping("/test1")
    String test1(@Collected(fieldName = "name") String name){
        return "test1";
    }
    @Collector
    @RequestMapping("/reg")
    String reg(User user){
        return "123";
    }
}
