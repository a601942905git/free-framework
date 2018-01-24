package com.java.redis;

import lombok.*;

import java.io.Serializable;

/**
 * com.java.redis.Person
 *
 * @author lipeng
 * @dateTime 2018/1/24 22:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable{

    private static final long serialVersionUID = 4790629220960802333L;

    private Integer id;

    private String name;

    private Integer age;
}
