package com.free.framework.soa.serialize.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.free.framework.soa.serialize.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * com.free.framework.soa.serialize.hessian.SerializeTest
 *
 * @author lipeng
 * @dateTime 2018/4/24 上午10:17
 */
public class SerializeTest {

    public static void main(String[] args) throws IOException {
        Person person = Person.builder().id(10001).name("test").build();
        byte[] bytes = jdkSerialize(person);
        person = jdkDeserialize(bytes);
        System.out.println(person);
    }

    public static <T> byte[] jdkSerialize(T obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(obj);
        return byteArrayOutputStream.toByteArray();
    }

    public static Person jdkDeserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        return (Person) hessianInput.readObject();
    }

    public static <T> byte[] hessianSerialize1(T obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.startMessage();
        hessian2Output.writeObject(obj);
        hessian2Output.completeMessage();
        hessian2Output.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static Person hessianDeserialize1(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        hessian2Input.startMessage();
        Person person = (Person) hessian2Input.readObject();
        hessian2Input.completeMessage();
        hessian2Input.close();
        return person;
    }
}
