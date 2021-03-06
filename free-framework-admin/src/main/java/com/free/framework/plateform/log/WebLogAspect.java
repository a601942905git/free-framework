package com.free.framework.plateform.log;

import com.free.framework.plateform.util.web.WebContextUtils;
import com.free.framework.util.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * com.free.framework.plateform.log.WebLogAspect
 * 系统日志切面
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /**
     * 存放时间戳本地线程
     */
    private static ThreadLocal<Long> SYSTEM_MILLIS_THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("execution(* com.free.framework.core.*.controller.*.*(..))")
    public void WebLogAspect() {

    }

    /**
     * 进入切入点之前执行
     * @param joinPoint
     */
    @Before("WebLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        SYSTEM_MILLIS_THREAD_LOCAL.set(DateUtils.getSystemMillis());
        Signature signature = joinPoint.getSignature();
        HttpServletRequest request = WebContextUtils.getRequest();

        log.info("==================请求开始=====================");
        log.info("Request IP:{}", request.getRemoteAddr());
        log.info("Request URL:{}", request.getRequestURL());
        log.info("Request Method:{}", request.getMethod());
        log.info("Request Class Method:{}", signature.getDeclaringTypeName() + "." + signature.getName());
        log.info("Request Method Args:{}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 切入点方法返回之后执行
     * @param joinPoint
     */
    @AfterReturning("WebLogAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Long diffSystemMillis = DateUtils.getSystemMillis() - SYSTEM_MILLIS_THREAD_LOCAL.get();
        SYSTEM_MILLIS_THREAD_LOCAL.remove();
        log.info("Request Execute Time:{}", diffSystemMillis);
        log.info("==================请求结束=====================\n");
    }

    /**
     * 切入点方法抛出异常之后执行
     * @param joinPoint
     */
    @AfterThrowing("WebLogAspect()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        log.info("执行doAfterThrowing方法======>");
    }
}
