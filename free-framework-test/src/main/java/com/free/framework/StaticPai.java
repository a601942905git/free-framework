package com.free.framework;

/**
 * com.free.framework.StaticPai
 *
 * @author lipeng
 * @dateTime 2018/9/5 下午5:10
 */
public class StaticPai {

    public static void main(String[] args) {
        Person1 person = new Woman();
        person.say();

        person = new Man();
        person.say();

        person = new Person1();
        person.say();
    }

}

class Person1 {

    public void say() {
        System.out.println("person say");
    }
}

class Woman extends Person1 {

    @Override
    public void say() {
        System.out.println("woman say");
    }
}

class Man extends Person1 {

    @Override
    public void say() {
        System.out.println("man say");
    }
}
