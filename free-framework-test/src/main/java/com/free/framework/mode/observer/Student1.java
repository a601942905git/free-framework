package com.free.framework.mode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * com.free.framework.mode.observer.Student1
 *
 * @author lipeng
 * @dateTime 2017/12/20 22:11
 */
public class Student1 implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("学生1,老师我听懂了......");
    }
}
