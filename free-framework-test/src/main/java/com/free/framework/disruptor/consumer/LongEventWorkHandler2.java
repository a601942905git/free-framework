package com.free.framework.disruptor.consumer;

import com.free.framework.disruptor.LongEvent;
import com.lmax.disruptor.WorkHandler;

/**
 * com.free.framework.disruptor.consumer.LongEventWorkHandler2
 *
 * @author lipeng
 * @dateTime 2018/3/3 11:22
 */
public class LongEventWorkHandler2 implements WorkHandler<LongEvent>{

    private String name;

    public LongEventWorkHandler2(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        System.out.println("名称为" + name + "，处理结果:" + longEvent.getValue());
    }
}
