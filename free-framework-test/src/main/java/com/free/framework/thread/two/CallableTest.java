package com.free.framework.thread.two;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * com.free.framework.thread.two.CallableTest
 *
 * @author lipeng
 * @dateTime 2018/5/15 下午2:06
 */
public class CallableTest {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future future = EXECUTOR_SERVICE.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行callable");
            return 1 + 1;
        });

        System.out.println(future.get());
    }
}
