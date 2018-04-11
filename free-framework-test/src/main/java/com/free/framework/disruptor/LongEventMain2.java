package com.free.framework.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.free.framework.disruptor.LongEventMain1
 * 使用Lamda表达式实现
 * @author lipeng
 * @dateTime 2018/3/2 16:32
 */
public class LongEventMain2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        LongEventFactory longEventFactory = new LongEventFactory();

        int ringBufferSize = 1024;

        // 构建disruptor
        Disruptor<LongEvent> disruptor =
                new Disruptor(longEventFactory, ringBufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());

        // 设置消费者
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println(event.getValue()));

        // 启动disruptor
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            byteBuffer.putLong(0, i);
            ringBuffer.publishEvent((event, sequence) -> event.setValue(byteBuffer.getLong(0)));
            Thread.sleep(1000);
        }
    }
}
