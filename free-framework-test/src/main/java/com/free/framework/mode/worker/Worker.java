package com.free.framework.mode.worker;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * com.free.framework.mode.worker.Worker
 * Master-Worker模式中的Worker
 * Worker的任务是:执行队列中的任务,然后将执行结果存储起来
 * @author lipeng
 * @dateTime 2018/2/26 16:50
 */
public class Worker implements Runnable{

    private Queue<Object> workerQueue;

    private Map<String, Object> resultMap;

    public void setWorkerQueue(Queue<Object> workerQueue) {
        this.workerQueue = workerQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 处理队列任务
     * @param input
     * @return
     */
    public Object handlerQueue(Object input) {
        return input;
    }

    /**
     * 每个worker要做的事情
     * ①从队列中取出任务
     * ②处理取出的任务,将结果存入结果集中
     */
    @Override
    public void run() {
        while (true) {
            // 从队列中取出任务
            Object input = workerQueue.poll();
            if (Objects.isNull(input)) {break;}
            Object result = handlerQueue(input);
            resultMap.put(String.valueOf(input.hashCode()), result);
        }
    }
}
