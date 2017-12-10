package com.free.framework.plateform.util.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.util.web.ApplicationContextUtils
 * web系统应用上下文工具类
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 获取bean
     * @param clazz
     * @return
     */
    public static Object getBean(Class clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取bean
     * @param beanName
     * @param clazz
     * @return
     */
    public static Object getBean(String beanName, Class clazz) {
        return applicationContext.getBean(beanName, clazz);
    }
}
