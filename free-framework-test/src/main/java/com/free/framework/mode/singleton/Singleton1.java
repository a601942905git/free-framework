package com.free.framework.mode.singleton;

/**
 * com.free.framework.mode.singleton.Singleton1
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:11
 */
public class Singleton1 {

    private static Student student = null;

    private Singleton1() {

    }

    /**
     * 双重锁实现
     * @return
     */
    public static Student getInstance() {
        if (null == student) {
            synchronized (Singleton1.class) {
                if (null == student) {
                    student = new Student();
                }
            }
        }
        return student;
    }
}
