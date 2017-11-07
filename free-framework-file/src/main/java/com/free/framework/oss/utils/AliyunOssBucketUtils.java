package com.free.framework.oss.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * com.free.framework.oss.AliyunOssBucketUtils
 *
 * @author lipeng
 * @dateTime 2017/11/7 22:04
 */
@Slf4j
public class AliyunOssBucketUtils {

    /**
     * 创建bucket
     * @param bucketName    bucket名称
     */
    public static Bucket createDefaultBucketName(String bucketName) {
        OSSClient ossClient = AliyunOssUtils.createOssClient();
        Bucket bucket = ossClient.createBucket(bucketName);
        log.info("创建的bucket:{}", bucket);
        AliyunOssUtils.shutDownClient(ossClient);
        return bucket;
    }

    /**
     * 获取bucket列表信息
     * @return
     */
    public static List<Bucket> listBucket() {
        OSSClient ossClient = AliyunOssUtils.createOssClient();
        List<Bucket> buckets = ossClient.listBuckets();
        AliyunOssUtils.shutDownClient(ossClient);
        return buckets;
    }

    /**
     * 删除bucket
     * @param bucketName
     */
    public static void deleteBucket(String bucketName) {
        OSSClient ossClient = AliyunOssUtils.createOssClient();
        ossClient.deleteBucket(bucketName);
        log.info("删除的bucket:{}", bucketName);
        AliyunOssUtils.shutDownClient(ossClient);
    }

    /**
     * 判断bucket是否存在
     * @param bucketName
     * @return
     */
    public static boolean doesBucketExist(String bucketName) {
        OSSClient ossClient = AliyunOssUtils.createOssClient();
        boolean exists = ossClient.doesBucketExist(bucketName);
        AliyunOssUtils.shutDownClient(ossClient);
        return exists;
    }
}
