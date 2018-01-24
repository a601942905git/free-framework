package com.free.framework.jdk7.call;

import java.util.concurrent.*;

public class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "Hello Call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MyCallable myCallable = new MyCallable();

        Future<String> future = executorService.submit(myCallable);
        String result = future.get();
        System.out.println(result);

        /**
         * 如果将任务放入futureTask,那么返回值直接从futureTask中获取
         */
        /*FutureTask<String> futureTask = new FutureTask(myCallable);
        executorService.submit(futureTask);
        System.out.println(futureTask.get());*/

        /*FutureTask<String> futureTask = new FutureTask(myCallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());*/
    }
}
