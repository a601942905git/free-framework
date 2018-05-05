package com.free.framework.plateform.monitor;

import com.free.framework.util.date.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * com.free.framework.plateform.monitor.ServiceMonitor
 * 统计接口的调用次数和接口调用时间
 * @author lipeng
 * @dateTime 2018/5/5 15:37
 */
@Component
@Aspect
public class ServiceMonitor {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    @Before("execution(* com.free.framework.*.controller.*.*(..))")
    public void countServiceInvoke(JoinPoint joinPoint) {
        counterService.increment(joinPoint.getSignature() + "");
    }

    @Around("execution(* com.free.framework.*.controller.*.*(..))")
    public void latencyService(ProceedingJoinPoint pjp) throws Throwable {
        long start = DateUtils.getSystemMillis();
        pjp.proceed();
        long end = DateUtils.getSystemMillis();
        gaugeService.submit(pjp.getSignature().toString(), end - start);
    }
}
