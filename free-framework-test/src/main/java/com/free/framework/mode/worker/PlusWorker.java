package com.free.framework.mode.worker;

/**
 * com.free.framework.mode.worker.PlusWorker
 *
 * @author lipeng
 * @dateTime 2018/2/7 22:32
 */
public class PlusWorker extends Worker{

    @Override
    public Object handler(Object input) {
        Integer i = (Integer) input;
        return i * i * i;
    }
}
