package com.free.framework.plateform.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * com.free.framework.plateform.listener.CustomerInitListener
 *
 * @author lipeng
 * @dateTime 2018/8/8 下午10:09
 */
public class CustomerInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("自定义监听器启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("自定义监听器销毁");
    }
}
