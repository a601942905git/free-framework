package com.free.framework.soa.serialize;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * com.free.framework.soa.serialize.Person
 *
 * @author lipeng
 * @dateTime 2018/4/24 上午10:05
 */
@Data
@Builder
@ToString
public class Person implements Serializable {

    private Integer id;

    private String name;
}
