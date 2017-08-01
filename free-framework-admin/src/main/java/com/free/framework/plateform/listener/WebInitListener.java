package com.free.framework.plateform.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class WebInitListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        try {
            String serverName = event.getApplicationContext().getDisplayName();
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            Integer serverPort = event.getEmbeddedServletContainer().getPort();
            log.info("===============================================================\n\n");
            log.info("{}应用的服务器地址为:{}", serverName, serverIp + ":" + serverPort);
            log.info("===============================================================\n\n");
        } catch (UnknownHostException e) {
            log.error("【WebInitListener中的onApplicationEvent异常:】", e.fillInStackTrace());
        }
    }
}
