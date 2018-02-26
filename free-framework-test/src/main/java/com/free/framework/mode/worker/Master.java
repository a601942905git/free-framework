package com.free.framework.mode.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * com.free.framework.mode.worker.Master
 * Master-Worker中的Master,负责提交任务,并且汇总结果
 *
 * @author lipeng
 * @dateTime 2018/2/26 16:57
 */
public class Master {

    /**
     * 任务队列
     */
    private Queue<Object> queue = new ConcurrentLinkedQueue<>();

    /**
     * 执行任务的结果集
     */
    private Map<String, Object> resultMap = new ConcurrentHashMap<>();

    /**
     * Worker集合
     */
    private Map<String, Thread> threadMap = new HashMap<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public Master(Worker worker, int workerCount) {
        worker.setWorkerQueue(queue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < workerCount; i++) {
            threadMap.put(String.valueOf(i), new Thread(worker, String.valueOf(i)));
        }
    }

    /**
     * 添加任务
     *
     * @param worker
     */
    public void submit(Object worker) {
        queue.add(worker);
    }

    /**
     * worker开始工作
     */
    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

    /**
     * 是否执行完成
     *
     * @return
     */
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }
}
