package com.free.framework.proxy.statics;

/**
 * @author smile
 */
public class UserServiceProxy implements UserService{

    private UserService target;

    public UserServiceProxy(UserService userService) {
        this.target = userService;
    }

    @Override
    public String sayHello(String name) {
        String returnStr;
        System.out.println("调用目标对象方法之前执行逻辑......");
        returnStr = target.sayHello(name);
        System.out.println("执行目标方法返回值:" + returnStr);
        System.out.println("调用目标对象方法之后执行逻辑......");
        return returnStr;
    }
}
