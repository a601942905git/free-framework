package com.free.framework.plateform.config.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * com.free.framework.plateform.config.executor.ExecutorConfig
 *
 * @author lipeng
 * @dateTime 2017/12/16 11:00
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 核心线程数量
     */
    public static final Integer CORE_POOL_SIZE = 10;

    /**
     * 最大线程数量
     */
    public static final Integer MAX_POOL_SIZE = 200;

    /**
     * 队列容量
     */
    public static final Integer QUEEN_CAPACITY = 10;

    @Bean
    public Executor myAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEEN_CAPACITY);
        executor.setThreadNamePrefix("CustomerExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
