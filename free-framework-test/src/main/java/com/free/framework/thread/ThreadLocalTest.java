package com.free.framework.thread;

/**
 * com.free.framework.thread.ThreadLocalTest
 *
 * @author lipeng
 * @dateTime 2018/5/15 上午10:42
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal当前线程共享数据
     * 原理就是Thread里面有一个ThreadLocalMap
     * key为当前线程对象 value就是我们要设置的值
     * @param args
     */
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        threadLocal.set(2);
        threadLocal.set(3);
        threadLocal.set(4);
        threadLocal.set(5);

        // 结果为5
        System.out.println(threadLocal.get());
        threadLocal.remove();
    }
}
