package com.free.framework.mode.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * com.free.framework.mode.worker.Master
 *
 * @author lipeng
 * @dateTime 2018/2/7 22:26
 */
public class Master {

    /**
     * 任务队列
     */
    private Queue<Object> queue = new ConcurrentLinkedQueue<>();

    /**
     * 任务执行结果集
     */
    private Map<String, Object> resultMap = new ConcurrentHashMap<>();

    /**
     * 子任务
     */
    private Map<String, Thread> threadMap = new HashMap<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public Master(Worker worker, int countWorker) {
        worker.setQueue(queue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < countWorker; i++) {
            threadMap.put(String.valueOf(i), new Thread(worker, String.valueOf(i)));
        }
    }

    public void submit(Object input) {
        queue.add(input);
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }
}
