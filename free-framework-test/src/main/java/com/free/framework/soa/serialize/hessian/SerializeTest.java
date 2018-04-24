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
        byte[] bytes = serialize1(person);
        person = deserialize1(bytes);
        System.out.println(person);
    }

    public static byte[] serialize(Person person) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(person);
        return byteArrayOutputStream.toByteArray();
    }

    public static Person deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        return (Person) hessianInput.readObject();
    }

    public static byte[] serialize1(Person person) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.startMessage();
        hessian2Output.writeObject(person);
        hessian2Output.completeMessage();
        hessian2Output.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static Person deserialize1(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        hessian2Input.startMessage();
        Person person = (Person) hessian2Input.readObject();
        hessian2Input.completeMessage();
        hessian2Input.close();
        return person;
    }
}
