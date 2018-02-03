package com.free.framework.guava.async;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * com.free.framework.guava.async.AsyncTest
 * 异步执行
 * @author lipeng
 * @dateTime 2018/2/3 15:03
 */
public class AsyncTest {
    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("执行任务一");
                return "执行任务一返回结果";
            } catch (InterruptedException e) {
                return "执行任务异常";
            }
        });

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("任务一执行成功");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("任务一执行失败");
            }
        }, listeningExecutorService);

        System.out.println("哈哈");
    }
}
