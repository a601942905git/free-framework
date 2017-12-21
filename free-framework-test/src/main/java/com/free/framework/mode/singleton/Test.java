package com.free.framework.mode.singleton;

/**
 * com.free.framework.mode.singleton.Test
 *
 * @author lipeng
 * @dateTime 2017/12/21 22:14
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 1000; i++) {
           /* new Thread(Singleton1::getInstance).start();
            new Thread(Singleton2::getInstance).start();*/
            new Thread(() -> {
                Singleton3 INSTANCE = Singleton3.INSTANCE;
            }).start();
        }
    }
}
