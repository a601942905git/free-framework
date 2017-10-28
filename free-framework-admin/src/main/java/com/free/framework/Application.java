package com.free.framework;

import com.free.framework.plateform.util.web.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/6/3.
 */
@SpringBootApplication
@Slf4j
public class Application implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    /**
     * 应用名
     */
    private static String applicationName;

    /**
     * 端口
     */
    private static Integer port;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
        ServerProperties serverProperties = (ServerProperties) ApplicationContextUtils.getBean(ServerProperties.class);
        applicationName = serverProperties.getDisplayName();
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("=================================");
        log.info("      项目名称:" + applicationName);
        log.info("      项目访问地址:" + ip + ":" + port);
        log.info("=================================");
    }
}
