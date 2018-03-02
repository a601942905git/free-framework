package com.free.framework.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * com.free.framework.disruptor.ClearObjectEventHandler
 *
 * @author lipeng
 * @dateTime 2018/3/2 16:58
 */
public class ClearObjectEventHandler implements EventHandler<ClearObjectEvent> {

    @Override
    public void onEvent(ClearObjectEvent clearObjectEvent, long l, boolean b) throws Exception {
        clearObjectEvent.clear();
    }
}
