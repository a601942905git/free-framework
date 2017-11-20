package com.free.framwork.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象,不需要实现接口,但是要指定接口类型
 * @author smile
 */
public class ProxyFactory {

    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     * @return
     */
    public Object getProxyInstance() {

        /**
         * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
         * ClassLoader loader:指定当前目标对象使用类加载器,获取加载器的方法是固定的
         * Class<?>[] interfaces:目标对象实现的接口的类型,使用泛型方式确认类型
         * InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
         */
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("目标方法执行开始======>");
                    // 执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("目标方法执行结果:" + returnValue);
                    System.out.println("目标方法执行结束======>");
                    return returnValue;
                }
        );
    }
}
