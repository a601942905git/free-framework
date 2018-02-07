package com.free.framework.mode.worker;

import java.util.Map;
import java.util.Queue;

/**
 * com.free.framework.mode.worker.Worker
 * 处理子任务机器
 * @author lipeng
 * @dateTime 2018/2/7 22:21
 */
public class Worker implements Runnable{

    /**
     * 任务队列
     */
    private Queue<Object> queue;

    /**
     * 处理结果集
     */
    private Map<String, Object> resultMap;

    public void setQueue(Queue<Object> queue) {
        this.queue = queue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handler(Object input) {
        return input;
    }

    @Override
    public void run() {
        while (true) {
            // 从队列中获取任务
            Object input = queue.poll();
            if (null == input) {continue;}
            // 执行任务
            Object result = handler(input);
            // 执行结果放入结果集中
            resultMap.put(String.valueOf(input.hashCode()), input);
        }
    }
}
