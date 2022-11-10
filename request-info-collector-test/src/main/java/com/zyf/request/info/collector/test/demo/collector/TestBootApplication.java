package com.zyf.request.info.collector.test.demo.collector;

import com.zyf.request.info.collector.core.collector.annotation.EnableRequestInfoCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author IvanZhang
 */
@SpringBootApplication
@EnableRequestInfoCollector
public class TestBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestBootApplication.class,args);
    }
}
