package com.free.framework.oss.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.free.framework.oss.Oss;
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
        return new OSSClient(Oss.endPoint, Oss.accessKeyId, Oss.accessKeySecret);
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
        if (StringUtils.isEmpty(Oss.endPoint)) {
            throw new OSSException("请调用Oss.init()方法初始化endPoint");
        }

        if (StringUtils.isEmpty(Oss.accessKeyId)) {
            throw new OSSException("请调用Oss.init()方法初始化accessKeyId");
        }

        if (StringUtils.isEmpty(Oss.accessKeySecret)) {
            throw new OSSException("请调用Oss.init()方法初始化accessKeySecret");
        }
    }
}
