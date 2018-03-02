package com.free.framework.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.free.framework.disruptor.LongEventMain
 *
 * @author lipeng
 * @dateTime 2018/3/2 16:12
 */
public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        LongEventFactory eventFactory = new LongEventFactory();

        int ringBufferSize = 1024;

        // 构建disruptor
        Disruptor disruptor = new Disruptor(eventFactory, ringBufferSize, executor);

        // 设置消费者
        disruptor.handleEventsWith(new LongEventHandler());

        // 启动disruptor
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            byteBuffer.putLong(0, i);
            longEventProducer.onData(byteBuffer);
            Thread.sleep(1000);
        }
    }
}
