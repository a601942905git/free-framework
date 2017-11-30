package com.free.framework.plateform.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.LongAdder;


/**
 * @author smile
 * 动态数据源切面
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    /**
     * 计数器
     */
    LongAdder longAdder = new LongAdder();

    public static final Long MAX_COUNT = 10000L;

    /**
     * 默认只读方法
     */
    private static final String[] SERVICE_METHOD = {"GET","LIST","FIND","SEARCH", "LIST", "COUNT"};


    /**
     * 切入点
     */
    @Pointcut("execution(* com.free.framework.core.*.service.*.*(..))")
    public void pointCutMethod() {

    }

    /**
     * 在切入点方法执行前后加入逻辑
     * @param pjp
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object obj = null;
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Object target = pjp.getTarget();
        try {
            Method method = target.getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
            String methodName = method.getName();
            setDataSourceType(methodName);
            //执行目标方法
            obj = pjp.proceed();
            //释放当前线程中的资源
            DataSourceHolder.removeDataSourceKey();
        } catch (NoSuchMethodException e) {
            log.error("======>DynamicDataSourceAspect.doAround,NoSuchMethodException:{}", e);
        } catch (Throwable throwable) {
            log.error("======>DynamicDataSourceAspect.doAround,throwable:{}", throwable);
        }
        return obj;
    }

    /**
     * 设置数据源类型
     * @param methodName
     */
    private void setDataSourceType(String methodName){
        //写操作,走主库
        if(isWriteOrRead(methodName)){
            DataSourceHolder.setDataSourceKey("master");
            System.out.println("=================>方法名为:" + methodName + "执行的是Master");
        }else{
            longAdder.increment();
            Long count = longAdder.longValue();
            if(count >= MAX_COUNT){
                longAdder.reset();
            }

            Long index = count % 2;
            DataSourceHolder.setDataSourceKey("slave" + (index + 1));
            System.out.println("=================>方法名为:" + methodName + "执行的是Slave" + (index + 1));
        }
    }

    /**
     * 判断当前方法是只读还是写操作
     * 写操作返回true,读操作返回false
     * @return
     */
    private boolean isWriteOrRead(String methodName){
        //默认写操作
        boolean flag = true;
        for (int i = 0; i < SERVICE_METHOD.length; i++) {
            if(StringUtils.isNotEmpty(methodName) && methodName.toUpperCase().startsWith(SERVICE_METHOD[i])){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
