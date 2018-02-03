package com.free.framework.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * com.free.framework.cache.CacheTest
 *
 * @author lipeng
 * @dateTime 2018/2/2 16:52
 */
public class CacheTest {

    public static final String CACHE_ENTITY_KEY = "cacheEntityList";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheData cacheData = new CacheData();
        LoadingCache<String, List<CacheEntity>> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, List<CacheEntity>>() {
                    @Override
                    public List<CacheEntity> load(String key) {
                        return cacheData.getCacheList();
                    }
                });

        // 第一次从缓存中获取,未命中,通过CacheLoader的load方法加载数据源,并且存入缓存中
        List<CacheEntity> cacheEntityList = cacheBuilder.get(CACHE_ENTITY_KEY);
        cacheEntityList.stream().forEach(System.out::println);
        System.out.println("沉睡2s");
        Thread.sleep(800);
        System.out.println("苏醒");

        Long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            System.out.println("========================");
            // 第二次命中缓存直接从缓存中获取数据,不进行数据库查询
            cacheEntityList = cacheBuilder.get(CACHE_ENTITY_KEY);
            cacheEntityList.stream().forEach(System.out::println);
            System.out.println("========================\n");
        }
        Long time2 = System.currentTimeMillis();
        System.out.println("耗时：" + (time2 - time1));

        Thread.sleep(500);
        System.out.println("===========2222=============");
        // 第二次命中缓存直接从缓存中获取数据,不进行数据库查询
        cacheEntityList = cacheBuilder.get(CACHE_ENTITY_KEY);
        cacheEntityList.stream().forEach(System.out::println);
        System.out.println("============22222============\n");
    }
}
