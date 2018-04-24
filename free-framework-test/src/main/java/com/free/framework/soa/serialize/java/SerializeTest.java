package com.free.framework.soa.serialize.java;

import com.free.framework.soa.serialize.Person;

import java.io.*;

/**
 * com.free.framework.soa.serialize.java.SerializeTest
 * 基于jdk实现序列化、反序列化
 * @author lipeng
 * @dateTime 2018/4/24 上午10:04
 */
public class SerializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = Person.builder().id(10001).name("test").build();
        byte[] bytes = serialize(person);
        person = deserialize(bytes);
        System.out.println(person);
    }

    public static byte[] serialize(Person person) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(person);
        return byteArrayOutputStream.toByteArray();
    }

    public static Person deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Person) objectInputStream.readObject();
    }
}
