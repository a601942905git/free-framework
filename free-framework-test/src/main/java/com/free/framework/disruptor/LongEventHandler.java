package com.free.framework.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * com.free.framework.disruptor.LongEventHandler
 * 消费者
 * @author lipeng
 * @dateTime 2018/3/2 16:01
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * 消费者消费数据
     * @param longEvent
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
