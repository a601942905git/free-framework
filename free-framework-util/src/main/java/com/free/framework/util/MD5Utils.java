package com.free.framework.util;

import java.security.MessageDigest;

/**
 * com.free.framework.util.MD5Utils
 *
 * @author lipeng
 * @dateTime 2017/9/17 3:17
 */
public class MD5Utils {

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return encode结果
     */
    public static String encode(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
