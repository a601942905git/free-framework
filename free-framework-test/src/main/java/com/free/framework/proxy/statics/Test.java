package com.free.framework.proxy.statics;

/**
 * @author smile
 */
public class Test {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.sayHello("smile");
    }
}
