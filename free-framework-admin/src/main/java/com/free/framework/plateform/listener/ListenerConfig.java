package com.free.framework.plateform.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.free.framework.plateform.listener.ListenerConfig
 *
 * @author lipeng
 * @dateTime 2018/1/9 10:10
 */
@Configuration
public class ListenerConfig {

    @Bean
    public WebInitListener webInitListener() {
        return new WebInitListener();
    }
}
