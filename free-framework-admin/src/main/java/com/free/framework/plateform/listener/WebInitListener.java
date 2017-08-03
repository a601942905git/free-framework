package com.free.framework.plateform.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebInitListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {

    }
}
