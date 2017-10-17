package com.free.framework.util.csrf;

import java.util.UUID;

/**
 * csrf工具类
 * @author lipeng
 */
public class CsrfTokenUtils {

    /**
     * 生成token
     * @return
     */
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
