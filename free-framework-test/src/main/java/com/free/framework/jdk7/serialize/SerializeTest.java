package com.free.framework.jdk7.serialize;

import com.free.framework.entity.Student;

import java.io.*;
import java.util.Date;

/**
 * com.free.framework.jdk7.serialize.SerializeTest
 *
 * @author lipeng
 * @dateTime 2017/11/19 20:57
 */
public class SerializeTest {

    public static void main(String[] args) {
        Student student = Student.builder()
                .id(10001)
                .name("测试")
                .age(22)
                .birthday(new Date())
                .build();

        byte[] objectBytes = serialize(student);
        System.out.println("序列化结果======>" + objectBytes);

        Student student1 = (Student) deserialize(objectBytes);
        System.out.println("反序列化结果=====>" + student1);
    }

    private static byte[] serialize(Object object) {
        byte[] objectBytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(object);
            objectBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectBytes;
    }

    private static Object deserialize(byte[] objectBytes) {
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
