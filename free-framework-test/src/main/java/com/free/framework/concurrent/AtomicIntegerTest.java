package com.free.framework.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.free.framework.concurrent.AtomicIntegerTest
 *
 * @author lipeng
 * @dateTime 2018/1/3 21:45
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        // 获取当前值,结果为:0
        System.out.println("当前值:" + atomicInteger.get());

        System.out.println("\n=============先取值后相加================");

        // 先获取当前值,然后进行+1操作 结果为:0
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println("加1之后的结果为:" + atomicInteger.get());

        System.out.println("\n==============先相加后取值===============");
        // 先加然后得到当前值
        System.out.println("先加1然后获取当前值的结果:" + atomicInteger.incrementAndGet());

        System.out.println("\n===============给定计算表达式进行计算,left 为当前值,right为给定的初始值==============");
        System.out.println(atomicInteger.accumulateAndGet(12, (left, right) -> {
            System.out.println(left);
            System.out.println(right);
            return left + right;
        }));

        System.out.println("\n===============当前值如果和期望值一样,那么更新当前值为update的值==============");
        System.out.println(atomicInteger.compareAndSet(14, 18));
        System.out.println(atomicInteger.get());

        System.out.println("\n设置当前值");
        atomicInteger.set(22);
        System.out.println(atomicInteger.get());

        System.out.println("\n更新值");
        atomicInteger.updateAndGet((x) -> 66);
        System.out.println(atomicInteger.get());

        System.out.println("\n延迟设置");
        atomicInteger.lazySet(88);
        System.out.println(atomicInteger.get());

        atomicInteger.weakCompareAndSet(87, 99);
        System.out.println(atomicInteger.get());
    }
}
