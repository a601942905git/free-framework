package com.free.framework.mode.observer;

import java.util.Observable;

/**
 * com.free.framework.mode.observer.Teacher
 *
 * @author lipeng
 * @dateTime 2017/12/20 22:11
 */
public class Teacher extends Observable{

    /**
     * 老师在讲台上询问大家听懂这道题了嘛
     * 首先设置改变
     * 然后通知所有的学生
     */
    public void ask() {
        System.out.println("这道题大家都听懂了吗?");
        setChanged();
        notifyObservers();
    }
}
