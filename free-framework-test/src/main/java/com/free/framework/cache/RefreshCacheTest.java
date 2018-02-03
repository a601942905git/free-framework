package com.free.framework.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * com.free.framework.cache.RefreshCacheTest
 * 测试Guava Cache刷新缓存
 * @author lipeng
 * @dateTime 2018/2/3 13:49
 */
public class RefreshCacheTest {
    final static Executor executor = Executors.newFixedThreadPool(5);

    /**
     * 异步刷新缓存,当有线程访问缓存中的数据的时候,发现缓存中的数据过期了,会调用异步刷新方法来进行缓存刷新
     * 但是当有多个线程请求已经失效的缓存的时候,只有一个线程用来刷新缓存数据,其余线程都返回旧数据用于展示
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        String randomStr = UUID.randomUUID().toString();
                        asyncReloading(this, executor);
                        return randomStr;
                    }
                });
        System.out.println("第一次获取缓存数据:" + cache.get("randomStr"));
        Thread.sleep(6000);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("第二次获取缓存数据:" + cache.get("randomStr"));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
