package com.java.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

@Slf4j
public class TestZookeeper {
    public static void main(String[] args) {
        log.debug("【测试debug日志】");
        log.info("【测试info日志】");
        log.error("【测试error日志】");
        final String ZOOKEEPER_SERVER = "192.168.1.13:2181,192.168.1.13:2182,192.168.1.13:2183";
        final int SESSION_TIME_OUT = 3000;
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_SERVER, SESSION_TIME_OUT, (watchedEvent) -> {
                System.out.println("已经触发了" + watchedEvent.getType() + "事件！");
            });


        } catch (IOException e) {
            log.error("【TestZookeeper连接zookeeper异常】:", e.fillInStackTrace());
        }
    }
}
