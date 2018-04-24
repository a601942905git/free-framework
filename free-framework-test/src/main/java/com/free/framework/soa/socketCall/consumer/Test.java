package com.free.framework.soa.socketCall.consumer;

import com.free.framework.soa.socketCall.service.SayHelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * com.free.framework.soa.socketCall.consumer.Test
 *
 * @author lipeng
 * @dateTime 2018/4/24 上午11:00
 */
public class Test {

    public static void main(String[] args) throws IOException, NoSuchMethodException, ClassNotFoundException {
        // 远程调用的接口
        String interfaceName = SayHelloService.class.getName();
        // 远程调用方法
        Method method = SayHelloService.class.getMethod("say", java.lang.String.class);
        // 远程方法参数信息
        Object[] arguments = {"hello"};

        Socket socket = new Socket("127.0.0.1", 1234);

        // 将调用的接口、方法名、方法参数传送到远端
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeUTF(interfaceName);
        out.writeUTF(method.getName());
        out.writeObject(method.getParameterTypes());
        out.writeObject(arguments);

        // 从远端读取方法执行结果
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object result = objectInputStream.readObject();
        System.out.println(result);
    }
}
