package com.free.framework.disruptor;

/**
 * com.free.framework.disruptor.ClearObjectEvent
 *
 * @author lipeng
 * @dateTime 2018/3/3 10:58
 */
public class ClearObjectEvent<T> {

    T value;

    void clear() {
        value = null;
    }
}
