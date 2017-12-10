package com.free.framework.plateform.common.controller;


import com.free.framework.plateform.util.web.WebContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * com.free.framework.plateform.common.controller.BaseController
 * 公用请求控制器
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
public class BaseController {

    /**
     * 前端请求参数id
     */
    public static final String ID = "id";

    public static Object getRequestAttribute(String key) {
        return getRequest().getAttribute(key);
    }

    public static void setRequestAttribute(String key, Object value) {
        getRequest().setAttribute(key, value);
    }

    public static Object getSessionAttribute(String key) {
        return getSession().getAttribute(key);
    }

    public static void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static HttpServletRequest getRequest() {
        return WebContextUtils.getRequest();
    }

    public static HttpServletResponse getResponse() {
        return WebContextUtils.getResponse();
    }

    public static HttpSession getSession() {
        return WebContextUtils.getSession();
    }
}
