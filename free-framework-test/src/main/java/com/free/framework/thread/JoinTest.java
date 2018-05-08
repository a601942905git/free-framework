package com.free.framework.thread;

/**
 * com.free.framework.thread.JoinTest
 *
 * @author lipeng
 * @dateTime 2018/5/8 上午11:23
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("线程1：" + i);
            }
        });

        /**
         * 调用join()方法的线程能够在此之前执行完毕
         * 类似阻塞，直到调用该方法的的线程执行完毕
         */
        thread.start();
        System.out.println("主线程。。。。。。");
    }
}
