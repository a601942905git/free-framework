package com.free.framework.mode.worker;

import java.util.Map;
import java.util.Set;

/**
 * com.free.framework.mode.worker.TestMasterWorker
 *
 * @author lipeng
 * @dateTime 2018/2/7 22:33
 */
public class TestMasterWorker {

    public static void main(String[] args) {
        Worker worker = new PlusWorker();
        Master master = new Master(worker, 4);
        for (int i = 0; i < 100; i++) {
            master.submit(i);
        }
        master.execute();

        Map<String, Object> resultMap = master.getResultMap();

        int re = 0;  //最终计算结果保存在此
        //不需要等待所有Worker都执行完即可
        while(true) {
            Set<String> keys = resultMap.keySet();  //开始计算最终结果
            String key = null;
            for(String k : keys) {
                key = k;
                break;
            }
            Integer i = null;
            if(key != null)
                i = (Integer)resultMap.get(key);
            if(i != null)
                re += i; //最终结果
            if(key != null)
                resultMap.remove(key); //移除已被计算过的项目
            if(master.isComplete() && resultMap.size()==0)
                break;
        }
        System.out.println(re);
    }
}
