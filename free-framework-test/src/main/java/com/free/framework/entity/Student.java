package com.free.framework.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * com.free.framework.entity.Student
 *
 * @author lipeng
 * @dateTime 2017/11/19 21:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private Date birthday;
}
