package com.java.test;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setId(10001);
        student1.setName("测试1");
        student1.setAge(22);

        Student student2 = Student.builder()
                .id(10002)
                .name("测试2")
                .age(22)
                .build();

        Student student3 = new Student(100003, "测试3", 22);

        System.out.println("student1===>" + student1);
        System.out.println("student2===>" + student2);
        System.out.println("student3===>" + student3);
    }
}
