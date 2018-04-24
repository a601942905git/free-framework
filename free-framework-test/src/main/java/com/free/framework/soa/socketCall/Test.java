package com.free.framework.soa.socketCall;

import com.free.framework.soa.socketCall.service.SayHelloService;
import com.free.framework.soa.socketCall.service.impl.SayHelloServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * com.free.framework.soa.socketCall.Test
 *
 * @author lipeng
 * @dateTime 2018/4/24 下午1:36
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(SayHelloService.class.getName());
        Class clazz = Class.forName("com.free.framework.soa.socketCall.service.SayHelloService");
        Method[] methods = clazz.getMethods();
        Method method = clazz.getMethod("say", new Class<?>[]{java.lang.String.class});
        SayHelloService service = new SayHelloServiceImpl();
        System.out.println(method.invoke(service, new Object[]{"hello"}));
    }
}
