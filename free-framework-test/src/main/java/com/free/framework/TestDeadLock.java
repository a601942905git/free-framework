package com.free.framework;

/**
 * com.free.framework.TestDeadLock
 *
 * @author lipeng
 * @dateTime 2018/8/30 下午5:08
 */
public class TestDeadLock {

    private static Object lock1 = new Object();

    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "获取lock1");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "获取lock2");
                }
            }
        }, "线程一");

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获取lock2");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "获取lock1");
                }
            }
        }, "线程二");

        t1.start();
        t2.start();
    }
}
