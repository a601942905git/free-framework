package com.free.framwork.proxy.dynamic;

/**
 * @author smile
 */
public class StudentServiceImpl implements StudentService{

    @Override
    public String sayHello(String name) {
        return "Welcome Student," + name;
    }
}
