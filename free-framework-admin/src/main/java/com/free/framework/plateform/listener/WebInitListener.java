package com.free.framework.plateform.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.listener.WebInitListener
 * web系统监听器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Slf4j
@Component
public class WebInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext().getParent()) {
            System.out.println("=======监听器开启监听======");
        }
    }
}
