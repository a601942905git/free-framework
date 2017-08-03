package com.free.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/6/3.
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("======Spring Boot项目启动开始======");
        SpringApplication.run(Application.class, args);
        log.info("======Spring Boot项目启动结束======");
    }
}
