package com.free.framework.disruptor.consumer;

import com.free.framework.disruptor.LongEvent;
import com.free.framework.disruptor.LongEventFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.free.framework.disruptor.LongEventMain1
 * 多消费者,每个
 * @author lipeng
 * @dateTime 2018/3/2 16:32
 */
public class LongEventMain4 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        LongEventFactory longEventFactory = new LongEventFactory();

        int ringBufferSize = 1024;

        // 构建disruptor
        Disruptor<LongEvent> disruptor =
                new Disruptor(longEventFactory, ringBufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());


        // 设置消费者
        disruptor.handleEventsWithWorkerPool(new LongEventWorkHandler2("测试2"), new LongEventWorkHandler1("测试1"));

        // 启动disruptor
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        System.out.println("开始执行.....");
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            byteBuffer.putLong(0, i);
            ringBuffer.publishEvent((event, sequence) -> event.setValue(byteBuffer.getLong(0)));
            Thread.sleep(1000);
        }

        System.out.println("结束执行......");
    }
}
