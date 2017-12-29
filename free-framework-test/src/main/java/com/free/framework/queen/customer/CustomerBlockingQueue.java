package com.free.framework.queen.customer;

import java.util.LinkedList;

/**
 * com.free.framework.queen.customer.CustomerBlockingQueue
 *
 * @author lipeng
 * @dateTime 2017/12/29 15:29
 */
public class CustomerBlockingQueue {

    private LinkedList<Object> linkedList = new LinkedList<>();

    private Integer maxValue = 100;

    private Integer minValue = 0;

    private Object object = new Object();

    /**
     * 往队列中添加元素
     */
    public void put(Object o) {
        synchronized (object) {
            try {
                // 队列已满
                while (linkedList.size() >= maxValue) {
                    System.out.println(Thread.currentThread().getName() + "，添加元素阻塞");
                    object.wait();
                }
                linkedList.add(o);
                System.out.println("往自定义阻塞队列中添加元素:" + o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                object.notify();
            }
        }
    }

    /**
     * 从队列中取元素
     */
    public void take() {
        synchronized (object) {
            try {
                while (linkedList.size() == minValue) {
                    System.out.println(Thread.currentThread().getName() + "，删除元素阻塞");
                    object.wait();
                }
                Object o = linkedList.removeFirst();
                System.out.println("从自定义阻塞队列中移除元素:" + (o));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                object.notify();
            }
        }
    }
}
