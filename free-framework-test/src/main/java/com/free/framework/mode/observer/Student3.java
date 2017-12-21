package com.free.framework.mode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * com.free.framework.mode.observer.Student3
 *
 * @author lipeng
 * @dateTime 2017/12/20 22:16
 */
public class Student3 implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("学生3,老师我听懂了......");
    }
}
