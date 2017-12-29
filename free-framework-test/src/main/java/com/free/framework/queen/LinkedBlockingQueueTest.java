package com.free.framework.queen;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * com.free.framework.queen.LinkedBlockingQueueTest
 *
 * @author lipeng
 * @dateTime 2017/12/28 17:50
 */
public class LinkedBlockingQueueTest {
    static BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();
    public static void main(String[] args) throws InterruptedException {
        Integer num = 0;
        for (int i = 0; i < 10; i++) {
            num = (i + 1);
            linkedBlockingQueue.put(num);
            System.out.println("往linkedBlockingQueue队列中添加元素:" + num);
            if (linkedBlockingQueue.size() > 0) {
                System.out.println("从linkedBlockingQueue队列中取出元素:" + linkedBlockingQueue.take());
            }
        }
    }
}
