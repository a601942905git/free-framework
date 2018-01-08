package com.free.framework.plateform.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * com.free.framework.plateform.listener.WebInitListener
 * web系统监听器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Slf4j
@WebListener
public class WebInitListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("===================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
