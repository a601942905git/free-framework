package com.free.framework.plateform.starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.starter.Starter1
 *
 * @author lipeng
 * @dateTime 2018/5/4 下午11:34
 */
@Component
@Order(2)
public class Starter1 implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("启动器2开始执行");
    }
}
