package com.free.framework.map;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * com.free.framework.map.ConcurrentMapTest
 *
 * @author lipeng
 * @dateTime 2018/7/12 上午10:16
 */
public class ConcurrentMapTest {


    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

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

        private ConcurrentHashMap<String, Integer> concurrentHashMap;

        private CountDownLatch countDownLatch;

        public ConcurrentHashMapRunnable(ConcurrentHashMap<String, Integer> concurrentHashMap,
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

            Integer newValue;
            while (true) {
                Integer oldValue = concurrentHashMap.get(mobile);
                if (null == oldValue) {
                    // put成功返回
                    if (Objects.isNull(concurrentHashMap.putIfAbsent(mobile, 1))) {
                        System.out.println("1=======>");
                        break;
                    }
                } else {
                    newValue = oldValue + 1;
                    System.out.println("2=======>" + newValue );
                    if (concurrentHashMap.replace(mobile, oldValue, newValue)) {
                        break;
                    }
                }
            }
        }
    }
}
