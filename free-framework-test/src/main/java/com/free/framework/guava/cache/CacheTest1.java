package com.free.framework.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * com.free.framework.cache.CacheTest1
 *
 * @author lipeng
 * @dateTime 2018/2/2 21:08
 */
public class CacheTest1 {

    public static final String CACHE_ENTITY_KEY = "cacheEntityList";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheData cacheData = new CacheData();
        Cache cache = CacheBuilder.newBuilder().build();
        List<CacheEntity> cacheEntityList = (List<CacheEntity>) cache.get(CACHE_ENTITY_KEY, () -> cacheData.getCacheList());
        cacheEntityList.stream().forEach(System.out::println);
        System.out.println(cache.size());
        System.out.println("沉睡2s");
        Thread.sleep(2000);
        System.out.println("苏醒");
        cacheEntityList = (List<CacheEntity>) cache.get(CACHE_ENTITY_KEY, () -> cacheData.getCacheList());
        cacheEntityList.stream().forEach(System.out::println);
        System.out.println(cache.size());
        cache.put("key1", "value1");
        System.out.println(cache.size());
    }


}
