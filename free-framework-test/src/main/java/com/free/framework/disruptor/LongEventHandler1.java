package com.free.framework.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * com.free.framework.disruptor.LongEventHandler1
 *
 * @author lipeng
 * @dateTime 2018/3/3 10:55
 */
public class LongEventHandler1 implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("LongEventHandler1处理结果:" + longEvent.getValue());
    }
}
