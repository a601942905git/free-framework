package com.free.framework.guava.limiter;


import com.google.common.util.concurrent.RateLimiter;

import java.time.Clock;

/**
 * com.free.framework.limit.RateLimiterTest
 *
 * @author lipeng
 * @dateTime 2018/2/3 14:45
 */
public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {
        testNoRateLimiter();
        System.out.println("==================华丽的分割线================");
        Thread.sleep(1000);
        testWithRateLimiter();
    }

    public static void testNoRateLimiter() {
        Long start = Clock.systemDefaultZone().millis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);
        }
        Long end = Clock.systemDefaultZone().millis();
        System.out.println(end - start);
    }

    public static void testWithRateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(10);
        Long start = Clock.systemDefaultZone().millis();
        for (int i = 0; i < 20; i++) {
            System.out.println(rateLimiter.acquire());
        }
        Long end = Clock.systemDefaultZone().millis();
        System.out.println(end - start);
    }
}
