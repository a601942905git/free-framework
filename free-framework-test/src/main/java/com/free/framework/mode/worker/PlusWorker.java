package com.free.framework.mode.worker;

/**
 * com.free.framework.mode.worker.PlusWorker
 * 具体任务Worker
 * @author lipeng
 * @dateTime 2018/2/26 16:55
 */
public class PlusWorker extends Worker{

    /**
     * 重写执行队列中任务的具体实现
     * @param input
     * @return
     */
    @Override
    public Object handlerQueue(Object input) {
        Integer plusNumber = (Integer) input;
        return plusNumber;
    }
}
