package com.free.framework.disruptor;

/**
 * com.free.framework.disruptor.ClearObjectEvent
 *
 * @author lipeng
 * @dateTime 2018/3/2 16:57
 */
public class ClearObjectEvent<T> {

    T val;

    void clear() {
        val = null;
    }
}
