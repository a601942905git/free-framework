package com.free.framework.plateform.util.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * web上下文
 * 获取request、response对象
 */
public class WebContextUtils {
    /**
     * 获取request对象
     * @return  request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

    /**
     * 获取session对象
     * @return  session对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 设置session
     * @param key       session的key
     * @param value     session的value
     */
    public static void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取session中的value
     * @param key   session的key
     * @return      session的value
     */
    public static Object getSessionAttribute(String key) {
        return getSession().getAttribute(key);
    }

    /**
     * 获取response对象
     * @return  response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        return response;
    }
}
