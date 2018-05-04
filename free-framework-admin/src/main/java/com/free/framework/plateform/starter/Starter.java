package com.free.framework.plateform.starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.starter.Starter
 * 在spring bean初始化完成 项目启动完毕之前执行 用于初始化资源
 * @author lipeng
 * @dateTime 2018/5/4 下午11:33
 */
@Component
@Order(1)
public class Starter implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("启动器1开始执行");
    }
}
