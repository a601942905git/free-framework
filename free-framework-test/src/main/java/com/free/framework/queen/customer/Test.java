package com.free.framework.queen.customer;

/**
 * com.free.framework.queen.customer.Test
 *
 * @author lipeng
 * @dateTime 2017/12/29 15:42
 */
public class Test {

    public static void main(String[] args) {
        CustomerBlockingQueue customerBlockingQueue = new CustomerBlockingQueue();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                customerBlockingQueue.put((i + 1));
            }
        },"put_thread").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                customerBlockingQueue.take();
            }
        },"take_thread").start();
    }
}
