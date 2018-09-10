package com.free.framework.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * com.free.framework.init.Test
 *
 * @author lipeng
 * @dateTime 2018/9/5 下午4:12
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {

        /**
         * 会进行类初始化
         */
        ClassInitTest1 classInitTest1 = new ClassInitTest1();
        ClassInitTest1.getName();
        ClassInitTest1.setName("123");
        Class test1Class = ClassInitTest1.class.getClassLoader().loadClass("com.free.framework.init.ClassInitTest1");
        Method method = test1Class.getMethod("test");
        Object object = test1Class.newInstance();
        method.invoke(object);

        /**
         * 不会进行类初始化
         */
        System.out.println(ClassInitTest1.ID);
        ClassInitTest1[] classInitTest1s = new ClassInitTest1[5];
        System.out.println(ClassInitTest1.test);

    }
}
