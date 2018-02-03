package com.free.framework.guava.cache;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * com.free.framework.cache.CacheData
 *
 * @author lipeng
 * @dateTime 2018/2/2 21:13
 */
public class CacheData {

    public List<CacheEntity> getCacheList() {
        System.out.println("模拟从数据库中读取数据......");
        List<CacheEntity> cacheEntityList = Lists.newArrayList();
        CacheEntity cacheEntity = CacheEntity.builder()
                .id(10001)
                .name("测试1")
                .age(22)
                .build();
        cacheEntityList.add(cacheEntity);

        cacheEntity = CacheEntity.builder()
                .id(10002)
                .name("测试2")
                .age(24)
                .build();
        cacheEntityList.add(cacheEntity);

        cacheEntity = CacheEntity.builder()
                .id(10003)
                .name("测试3")
                .age(26)
                .build();
        cacheEntityList.add(cacheEntity);
        return cacheEntityList;
    }
}
