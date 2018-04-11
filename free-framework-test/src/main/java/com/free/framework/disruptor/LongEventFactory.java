package com.free.framework.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * com.free.framework.disruptor.LongEventFactory
 * 事件工厂
 * @author lipeng
 * @dateTime 2018/3/2 16:04
 */
public class LongEventFactory implements EventFactory<LongEvent>{

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
