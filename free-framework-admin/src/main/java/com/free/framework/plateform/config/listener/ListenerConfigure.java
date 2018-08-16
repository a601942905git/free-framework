package com.free.framework.plateform.config.listener;

import com.free.framework.plateform.listener.CustomerInitListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.free.framework.plateform.config.listener.ListenerConfigure
 *
 * @author lipeng
 * @dateTime 2018/8/8 下午10:11
 */
@Configuration
public class ListenerConfigure {

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean () {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new CustomerInitListener());
        return servletListenerRegistrationBean;
    }
}
