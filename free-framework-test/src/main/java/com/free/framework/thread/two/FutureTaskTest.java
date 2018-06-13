package com.free.framework.thread.two;

import java.util.concurrent.*;

/**
 * com.free.framework.thread.FutureTaskTest
 *
 * @author lipeng
 * @dateTime 2018/5/15 上午11:36
 */
public class FutureTaskTest {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        FutureTask futureTask = new FutureTask(() -> 1 + 1);
//
//        futureTask.run();
//        System.out.println(futureTask.isDone());
//        System.out.println(futureTask.get());

        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            Thread.sleep(2000);
            return 100 + 100;
        });

        EXECUTOR_SERVICE.submit(futureTask);

        System.out.println(futureTask.get());
    }
}
