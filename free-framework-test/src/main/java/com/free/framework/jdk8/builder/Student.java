package com.free.framework.jdk8.builder;

import java.util.Date;

/**
 * com.free.framework.jdk8.builder.Student
 * Builder模式
 * @author lipeng
 * @dateTime 2017/8/26 12:17
 */
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.birthday = builder.birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    public static class Builder{

        private Integer id;

        private String name;

        private Integer age;

        private Date birthday;

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder age(Integer age){
            this.age = age;
            return this;
        }

        public Builder birthday(Date birthday){
            this.birthday = birthday;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
