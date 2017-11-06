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
 * 系统日志切面
 * @author lipeng
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /**
     * 存放时间戳本地线程
     */
    private static ThreadLocal<Long> SYSTEM_MILLIES_THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("execution(* com.free.framework.core.*.controller.*.*(..))")
    public void WebLogAspect() {

    }

    /**
     * 进入切入点之前执行
     * @param joinPoint
     */
    @Before("WebLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        SYSTEM_MILLIES_THREAD_LOCAL.set(DateUtils.getSystemMillis());
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
     * 在切入点方法执行前后加入逻辑
     * @param joinPoint
     */
    /*@Around("WebLogAspect()")
    public void doAround(JoinPoint joinPoint) {
        log.info("执行doAround方法======>");
    }*/

    /**
     * 切入点方法执行完毕返回之前执行
     * @param joinPoint
     */
   /* @After("WebLogAspect()")
    public void doAfter(JoinPoint joinPoint) {

    }*/

    /**
     * 切入点方法返回之后执行
     * @param joinPoint
     */
    @AfterReturning("WebLogAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Long diffSystemMillis = DateUtils.getSystemMillis() - SYSTEM_MILLIES_THREAD_LOCAL.get();
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
