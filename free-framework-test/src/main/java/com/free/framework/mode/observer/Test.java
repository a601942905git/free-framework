package com.free.framework.mode.observer;

/**
 * com.free.framework.mode.observer.Test
 *
 * @author lipeng
 * @dateTime 2017/12/20 22:17
 */
public class Test {

    public static void main(String[] args) {
        Student1 student1 = new Student1();
        Student2 student2 = new Student2();
        Student3 student3 = new Student3();
        Teacher teacher = new Teacher();
        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.addObserver(student3);

        teacher.ask();
    }
}
