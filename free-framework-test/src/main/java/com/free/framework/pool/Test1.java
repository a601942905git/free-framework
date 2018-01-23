package com.free.framework.pool;

import java.util.concurrent.ExecutionException;

/**
 * com.free.framework.pool.Test1
 *
 * @author lipeng
 * @dateTime 2018/1/23 15:22
 */
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long time1 = System.currentTimeMillis();
        try {
            for (int i = 0; i < 1000; i++) {
                // 此处模拟代码消耗时间
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
}
