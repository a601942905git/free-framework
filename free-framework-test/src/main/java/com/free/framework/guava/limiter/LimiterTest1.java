package com.free.framework.guava.limiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * com.free.framework.guava.limiter.LimiterTest1
 *
 * @author lipeng
 * @dateTime 2018/2/3 16:05
 */
public class LimiterTest1 {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5.0);
        System.out.println(rateLimiter.acquire(5));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(1));
    }
}
