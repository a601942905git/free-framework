package com.free.framework.proxy.cglib;

/**
 * @author smile
 */
public class Test {

    public static void main(String[] args) {
        // 目标对象
        PersonService target = new PersonService();

        // 代理对象
        PersonService proxy = (PersonService) new ProxyFactory(target).getProxyInstance();

        // 通过代理对象调用目标对象方法
        proxy.eating("smile");
    }
}
