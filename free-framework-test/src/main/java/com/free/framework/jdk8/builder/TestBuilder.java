package com.free.framework.jdk8.builder;

import java.util.Date;

/**
 * com.free.framework.jdk8.builder.TestBuilder
 *
 * @author lipeng
 * @dateTime 2017/8/26 12:25
 */
public class TestBuilder {

    public static void main(String[] args) {
        Student student = Student.Builder.builder()
                .id(10001)
                .name("测试")
                .age(22)
                .birthday(new Date())
                .build();

        Student student1 = Student.Builder.builder()
                .id(10002)
                .name("测试1")
                .age(22)
                .birthday(new Date())
                .build();

        System.out.println(student);
        System.out.println("======================");
        System.out.println(student1);
    }
}
