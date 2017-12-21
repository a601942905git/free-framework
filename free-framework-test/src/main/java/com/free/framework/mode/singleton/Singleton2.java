package com.free.framework.mode.singleton;

/**
 * com.free.framework.mode.singleton.Singleton2
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:31
 */
public class Singleton2 {

    private Singleton2() {

    }

    private static class SingletonHolder {
        private static Student student = new Student();
    }

    public static Student getInstance() {
        return SingletonHolder.student;
    }
}
