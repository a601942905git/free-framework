package com.free.framework.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * com.free.framework.pool.Test3
 *
 * @author lipeng
 * @dateTime 2018/1/23 15:57
 */
public class Test3 {

    static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
