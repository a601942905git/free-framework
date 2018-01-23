package com.free.framework.pool;

import java.util.concurrent.*;

/**
 * com.free.framework.pool.Test
 *
 * @author lipeng
 * @dateTime 2018/1/23 11:22
 */
public class Test {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    private static CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long time1 = System.currentTimeMillis();
        Callable<String> myTask;
        for (int i = 0; i < 10; i++) {
            myTask = new MyTask();
            executorService.submit(myTask);
        }
        Long time2 = System.currentTimeMillis();
        Long diffTime = time2 - time1;
        System.out.println("总耗时间:" + diffTime);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
