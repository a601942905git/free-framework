package com.free.framework.map;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * com.free.framework.map.HashMapTest
 *
 * @author lipeng
 * @dateTime 2018/7/12 上午10:53
 */
public class HashMapTest {

    private static AtomicLong atomicLong = new AtomicLong(1);

    private static HashMap<String, AtomicLong> concurrentHashMap = new HashMap<>();

    private static Integer LOOP_COUNT = 10;

    private static CountDownLatch countDownLatch = new CountDownLatch(LOOP_COUNT);

    public static final String MOBILE = "13260803899";

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < LOOP_COUNT; i++) {
            new Thread(new ConcurrentHashMapRunnable(concurrentHashMap, countDownLatch, MOBILE)).start();
        }

        Thread.sleep(1000);
        System.out.println(concurrentHashMap.get(MOBILE));
    }

    static class ConcurrentHashMapRunnable implements Runnable {

        private String mobile;

        private HashMap<String, AtomicLong> concurrentHashMap;

        private CountDownLatch countDownLatch;

        public ConcurrentHashMapRunnable(HashMap<String, AtomicLong> concurrentHashMap,
                                         CountDownLatch countDownLatch, String mobile) {
            this.concurrentHashMap = concurrentHashMap;
            this.countDownLatch = countDownLatch;
            this.mobile = mobile;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AtomicLong atomicLong1 = concurrentHashMap.get(mobile);
            if (Objects.isNull(atomicLong1)) {
                concurrentHashMap.put(mobile, atomicLong);
            } else {
                atomicLong.incrementAndGet();
                concurrentHashMap.put(mobile, atomicLong);
            }
        }
    }
}
