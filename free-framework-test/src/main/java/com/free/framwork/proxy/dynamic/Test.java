package com.free.framwork.proxy.dynamic;

/**
 * 测试
 * @author smile
 */
public class Test {

    public static void main(String[] args) {
        // 创建目标对象
        StudentService target = new StudentServiceImpl();
        System.out.println("目标对象:" + target.getClass());

        // 创建代理对象
        StudentService proxyInstance = (StudentService) new ProxyFactory(target).getProxyInstance();
        System.out.println("代理对象:" + proxyInstance.getClass());

        // 通过代理对象调用目标对象方法
        proxyInstance.sayHello("smile");
    }
}
