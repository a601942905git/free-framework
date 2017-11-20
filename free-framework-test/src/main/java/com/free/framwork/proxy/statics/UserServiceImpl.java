package com.free.framwork.proxy.statics;

/**
 * @author smile
 */
public class UserServiceImpl implements UserService{

    @Override
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
