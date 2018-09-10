package com.free.framework.thread.one;

import java.util.concurrent.Semaphore;

/**
 * com.free.framework.thread.SemaphoreTest
 *
 * @author lipeng
 * @dateTime 2018/5/14 上午11:33
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        // 8个工人
        Integer workerNumber = 8;
        // 5台机器
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < workerNumber; i++) {
            new Thread(new Worker((i + 1), semaphore)).start();
        }
    }

    static class Worker implements Runnable{

        private Integer number;

        private Semaphore semaphore;

        Worker(Integer number, Semaphore semaphore) {
            this.number = number;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(number + "号工人占用一台机器");
                // 模拟工人使用机器的时间
                Thread.sleep(number * 1000);
                semaphore.release();
                System.out.println(number + "号工人释放一台机器");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
