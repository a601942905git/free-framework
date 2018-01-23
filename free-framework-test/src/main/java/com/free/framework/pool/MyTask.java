package com.free.framework.pool;

import java.util.concurrent.Callable;

/**
 * com.free.framework.pool.MyTask
 *
 * @author lipeng
 * @dateTime 2018/1/23 14:57
 */
public class MyTask implements Callable<String>{

    public static final Integer COUNT = 1000;

    @Override
    public String call() {
        String number = "";
        try {
            for (int i = 0; i < COUNT; i++) {
                System.out.println("当前线程:" + Thread.currentThread().getName() + "=====>" + number);
                number = String.valueOf((i + 1));
                // 此处模拟代码消耗时间
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }
}
