package com.free.framework.thread.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.free.framework.thread.two.RunnableTest
 *
 * @author lipeng
 * @dateTime 2018/5/15 下午1:54
 */
public class RunnableTest {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        EXECUTOR_SERVICE.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable执行:" + (i + 1));
            }
        });
        EXECUTOR_SERVICE.shutdown();
    }
}
