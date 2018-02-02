package com.free.framework.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * com.free.framework.cache.CacheTest
 *
 * @author lipeng
 * @dateTime 2018/2/2 16:52
 */
public class CacheTest {

    public static final String CACHE_ENTITY_KEY = "cacheEntityList";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheTest cacheTest = new CacheTest();
        LoadingCache<String, List<CacheEntity>> cacheBuilder = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, List<CacheEntity>>() {
                    @Override
                    public List<CacheEntity> load(String key) {
                        return cacheTest.getCacheList();
                    }
                });

        // 第一次从缓存中获取,未命中,通过CacheLoader的load方法加载数据源,并且存入缓存中
        List<CacheEntity> cacheEntityList = cacheBuilder.get(CACHE_ENTITY_KEY);
        cacheEntityList.stream().forEach(System.out::println);
        System.out.println("沉睡2s");
        Thread.sleep(10000);
        System.out.println("苏醒");
        // 第二次命中缓存直接从缓存中获取数据,不进行数据库查询
        cacheEntityList = cacheBuilder.get(CACHE_ENTITY_KEY);
        cacheEntityList.stream().forEach(System.out::println);
    }

    private List<CacheEntity> getCacheList() {
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
