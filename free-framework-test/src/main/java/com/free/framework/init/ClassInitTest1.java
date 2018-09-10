package com.free.framework.init;

/**
 * com.free.framework.init.ClassInitTest1
 *
 * @author lipeng
 * @dateTime 2018/9/5 下午4:10
 */
public class ClassInitTest1 extends ClassInitTest1Parent{

    public static final String ID = "100001";

    public static String name;

    static {
        System.out.println("初始化ClassInitTest1");
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ClassInitTest1.name = name;
    }


    public void test() {

    }
}
