package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.Collector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IvanZhang
 */
@RestController
public class TestController {
    @Collector({"id","name"})
    @RequestMapping("/test")
    String test(){
        return "123";
    }
    @Collector({"name"})
    @RequestMapping("/test1")
    String test1(String name){
        return "test1";
    }
}
