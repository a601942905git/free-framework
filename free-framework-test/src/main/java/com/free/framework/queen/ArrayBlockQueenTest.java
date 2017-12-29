package com.free.framework.queen;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * com.free.framework.queen.ArrayBlockQueenTest
 *
 * @author lipeng
 * @dateTime 2017/12/28 17:36
 */
public class ArrayBlockQueenTest {

    private static BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Integer num = 0;
        Integer size = 10;
        for (int i = 0; i < 20; i++) {
            num = (i + 1);
            // 如果队列中添加到10个元素之后,当前线程阻塞
            arrayBlockingQueue.put(num);
            System.out.println("往arrayBlockingQueue队列中添加元素:" + num);
            size = arrayBlockingQueue.size();
            if (size > 0) {
                System.out.println("从arrayBlockingQueue队列中取出元素:" + arrayBlockingQueue.take());
            }
        }
        System.out.println("程序结束");
    }
}
