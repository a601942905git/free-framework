package com.free.framework.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * com.free.framework.thread.ThreadTest
 *
 * @author lipeng
 * @dateTime 2018/5/18 下午5:40
 */
public class ThreadTest {

    public static void main(String[] args) {
        /*System.out.println(Thread.holdsLock(ThreadTest.class));
        synchronized (ThreadTest.class) {
            System.out.println(Thread.holdsLock(ThreadTest.class));
        }*/
        Map map = new HashMap(6);
        map.put("test", "test");
    }
}
