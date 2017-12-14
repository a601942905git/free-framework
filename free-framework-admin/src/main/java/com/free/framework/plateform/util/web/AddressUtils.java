package com.free.framework.plateform.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * com.free.framework.plateform.util.web.AddressUtils
 *
 * @author lipeng
 * @dateTime 2017/12/14 11:13
 */
public class AddressUtils {

    /**
     * 获取请求ip地址
     * @return
     */
    public static String getIpAddress() {
        HttpServletRequest request = WebContextUtils.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
