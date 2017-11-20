package com.free.framwork.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理对象
 * @author smile
 */
public class ProxyFactory implements MethodInterceptor{

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     * @return
     */
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回到
        enhancer.setCallback(this);
        // 创建子类代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标对象方法执行开始======>");
        Object returnStr = method.invoke(target, objects);
        System.out.println("目标对象方法执行返回结果:" + returnStr);
        System.out.println("目标对象方法执行结束======>");
        return null;
    }
}
