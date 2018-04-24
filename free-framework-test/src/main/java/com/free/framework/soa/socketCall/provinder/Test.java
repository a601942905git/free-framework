package com.free.framework.soa.socketCall.provinder;

import com.free.framework.soa.socketCall.service.impl.SayHelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * com.free.framework.soa.socketCall.provinder.Test
 *
 * @author lipeng
 * @dateTime 2018/4/24 上午11:19
 */
public class Test {

    private static Map<String, Object> serviceMap = new HashMap<>();

    static {
        serviceMap.put("com.free.framework.soa.socketCall.service.SayHelloService", new SayHelloServiceImpl());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            Socket socket = serverSocket.accept();

            // 读取服务信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String interfaceName = objectInputStream.readUTF();
            String methodName = objectInputStream.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
            Object[] arguments = (Object[]) objectInputStream.readObject();

            // 通过反射执行远程调用的接口方法
            Class serviceInterface = Class.forName(interfaceName);
            Arrays.stream(serviceInterface.getMethods()).forEach(System.out::println);
            Object service = serviceMap.get(interfaceName);
            Method method = serviceInterface.getMethod(methodName, parameterTypes);
            Object result = method.invoke(service, arguments);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
        }
    }

}
