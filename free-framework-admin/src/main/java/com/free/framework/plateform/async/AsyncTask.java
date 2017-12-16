package com.free.framework.plateform.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * com.free.framework.plateform.async.AsyncTask
 *
 * @author lipeng
 * @dateTime 2017/12/16 11:13
 */
@Component
public class AsyncTask {

    @Async
    public void executeAsyncTask1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("异步执行任务1.....");
    }

    @Async
    public Future<String> executeAsyncTask2(String name) {
        System.out.println("异步执行任务二," + name);
        return new AsyncResult<>("异步执行任务二");
    }
}
