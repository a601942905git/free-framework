package com.free.framework.plateform.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;

import javax.servlet.annotation.WebListener;

/**
 * com.free.framework.plateform.listener.WebInitListener
 * web系统监听器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Slf4j
@WebListener
public class WebInitListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {

    }
}
