package com.free.framework.queen.customer;

import com.free.framework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.free.framework.queen.customer.CustomerBlockingQueue1
 *
 * @author lipeng
 * @dateTime 2018/9/28 上午11:21
 */
public class CustomerBlockingQueue1 {

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private LinkedList<Integer> list = new LinkedList<>();


    public void put(int num) throws InterruptedException {
        lock.lock();
        try {
            while (CollectionUtils.isNotEmpty(list)) {
                notFull.await();
            }
            TimeUnit.SECONDS.sleep(1);
            list.add(num);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",添加元素：" + num);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (CollectionUtils.isEmpty(list)) {
                notEmpty.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",移除元素：" + list.removeFirst());
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CustomerBlockingQueue1 customerBlockingQueue1 = new CustomerBlockingQueue1();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    customerBlockingQueue1.put((i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    customerBlockingQueue1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
