package com.free.framwork.jdk7.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer>{

    private static final Integer THRESHOLD = 2;

    private Integer start;

    private Integer end;

    public CountTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = end - start <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / THRESHOLD;
            // 分裂任务
            CountTask countTask1 = new CountTask(start, middle);
            CountTask countTask2 = new CountTask(middle + 1, end);

            // 执行子任务
            countTask1.fork();
            countTask2.fork();

            // 获取子任务执行结果
            int leftResult = countTask1.join();
            int rightResult = countTask2.join();

            // 合并子任务结果
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
