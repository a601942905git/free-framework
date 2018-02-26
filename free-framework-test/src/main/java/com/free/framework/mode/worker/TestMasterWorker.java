package com.free.framework.mode.worker;

import java.util.Map;

/**
 * com.free.framework.mode.worker.TestMasterWorker
 *
 * @author lipeng
 * @dateTime 2018/2/26 17:04
 */
public class TestMasterWorker {

    public static void main(String[] args) {
        Worker worker = new PlusWorker();
        Master master = new Master(worker, 4);
        for (int i = 0; i < 10; i++) {
            master.submit(i);
        }

        master.execute();

        Map<String, Object> resultMap = master.getResultMap();
        int plusNumberSum = 0;
        while (true) {
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                Integer result = (Integer) entry.getValue();
                plusNumberSum += result;
                resultMap.remove(entry.getKey());
            }

            if (master.isComplete()) {
                break;
            }
        }

        System.out.println("执行的结果:============>" + plusNumberSum);
    }
}
