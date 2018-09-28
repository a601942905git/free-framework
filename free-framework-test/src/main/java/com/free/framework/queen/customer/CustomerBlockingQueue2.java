package com.free.framework.queen.customer;

import com.free.framework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * com.free.framework.queen.customer.CustomerBlockingQueue2
 *
 * @author lipeng
 * @dateTime 2018/9/28 上午11:37
 */
public class CustomerBlockingQueue2 {

    private LinkedList<Integer> list = new LinkedList<>();


    public void put(int num) throws InterruptedException {
        synchronized (this) {
            while (CollectionUtils.isNotEmpty(list)) {
                this.wait();
            }
            TimeUnit.SECONDS.sleep(1);
            list.add(num);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",添加元素：" + num);
            this.notifyAll();
        }
    }

    public void take() throws InterruptedException {
        synchronized (this) {
            while (CollectionUtils.isEmpty(list)) {
                this.wait();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",移除元素：" + list.removeFirst());
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        CustomerBlockingQueue2 customerBlockingQueue2 = new CustomerBlockingQueue2();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    customerBlockingQueue2.put((i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    customerBlockingQueue2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
