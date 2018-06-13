package com.free.framework.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * com.free.framework.disruptor.LongEventProducer
 *
 * @author lipeng
 * @dateTime 2018/3/2 16:05
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public RingBuffer<LongEvent> getRingBuffer() {
        return ringBuffer;
    }

    public void setRingBuffer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        // 申请环形上的插槽,用来存放数据
        Long sequence = ringBuffer.next();
        System.out.println("当前序列号=============>" + sequence);

        try {
            // 插槽上要进行发送的事件
            LongEvent longEvent = ringBuffer.get(sequence);
            // 填充数据
            longEvent.setValue(byteBuffer.getLong(0));
        } finally {
            // 发布环形插槽上的事件
            ringBuffer.publish(sequence);
        }
    }
}
