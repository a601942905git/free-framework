package com.free.framework.oss.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.free.framework.oss.OssConfig;
import org.apache.commons.lang3.StringUtils;

/**
 * com.free.framework.oss.utils.AliyunOssUtils
 *
 * @author lipeng
 * @dateTime 2017/11/7 22:29
 */
public class AliyunOssUtils {

    /**
     * 获取OssClient对象
     * @return  OssClient
     */
    public static OSSClient createOssClient() {
        AliyunOssUtils.validateOss();
        return new OSSClient(OssConfig.endPoint, OssConfig.accessKeyId, OssConfig.accessKeySecret);
    }

    /**
     * 关闭OssClient对象
     * @param ossClient
     */
    public static void shutDownClient(OSSClient ossClient) {
        ossClient.shutdown();
    }

    /**
     * 校验Oss信息
     */
    public static void validateOss() {
        if (StringUtils.isEmpty(OssConfig.endPoint)) {
            throw new OSSException("请调用Oss.init()方法初始化endPoint");
        }

        if (StringUtils.isEmpty(OssConfig.accessKeyId)) {
            throw new OSSException("请调用Oss.init()方法初始化accessKeyId");
        }

        if (StringUtils.isEmpty(OssConfig.accessKeySecret)) {
            throw new OSSException("请调用Oss.init()方法初始化accessKeySecret");
        }
    }
}
