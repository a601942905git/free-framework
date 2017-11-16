package com.free.framework.oss;

/**
 * com.free.framework.oss.OssConfig
 *
 * @author lipeng
 * @dateTime 2017/11/7 22:23
 */
public class OssConfig {

    /**
     * 接入点
     */
    public static String endPoint;

    /**
     * oss key
     */
    public static String accessKeyId;

    /**
     * oss secret
     */
    public static String accessKeySecret;

    /**
     * 初始化oss信息
     * @param endPoint
     * @param accessKeyId
     * @param accessKeySecret
     */
    public static void init(String endPoint, String accessKeyId, String accessKeySecret) {
        OssConfig.endPoint = endPoint;
        OssConfig.accessKeyId = accessKeyId;
        OssConfig.accessKeySecret = accessKeySecret;
    }
}
