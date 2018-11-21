package com.free.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

/**
 * com.free.framework.Application
 * 系统启动类
 *
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public static void main(String[] args) throws UnknownHostException {
        Environment environment = SpringApplication.run(Application.class, args).getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("=================================");
        log.info("      项目名称:" + environment.getProperty("server.display-name"));
        log.info("      项目访问地址:" + ip + ":" + environment.getProperty("server.port"));
        log.info("=================================");
    }

    /**
     * 扫描URL，如果数据库中不存在，则保存入数据库
     * 这个注解很重要，可以在每次启动的时候检查是否有URL更新，
     * RequestMappingHandlerMapping只能在controller层用。这里我们放在主类中
     */
    @PostConstruct
    public void detectHandlerMethods() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> mappings = map.keySet();
        for (RequestMappingInfo info : mappings) {
            HandlerMethod method = map.get(info);
            String methodstr = method.toString();
            methodstr = methodstr.split("\\(")[0];
            methodstr = methodstr.split(" ")[2];
            int i = methodstr.lastIndexOf(".");
            methodstr = methodstr.substring(0, i);
            String urlparm = info.getPatternsCondition().toString();
            String url = urlparm.substring(1, urlparm.length() - 1);
        }
    }
}
