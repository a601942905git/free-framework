package com.free.framework;

import com.free.framework.jdk8.builder.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        /*List<String> names = new ArrayList<>();
        names.add("alone");
        names.add("jone");
        names.add("lucy");

        String[] namesArray = new String[names.size()];
        namesArray = names.toArray(namesArray);

        Arrays.stream(namesArray).forEach(System.out::println);*/

        List<Student> students = new ArrayList<>();
        students.add(Student.Builder.builder().id(10001).name("smile1").build());
        students.add(Student.Builder.builder().id(10002).name("smile2").build());
        students.add(Student.Builder.builder().id(10003).name("smile3").build());
        students.add(Student.Builder.builder().id(10004).name("smile4").build());
        students.add(Student.Builder.builder().id(10005).name("smile5").build());

        students = students.stream().map(student -> {student.setName(student.getName().toUpperCase());return student;}).collect(Collectors.toList());

        students.stream().forEach(System.out::println);
    }
}
