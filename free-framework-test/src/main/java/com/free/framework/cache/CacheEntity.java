package com.free.framework.cache;

import lombok.*;

/**
 * com.free.framework.cache.CacheEntity
 *
 * @author lipeng
 * @dateTime 2018/2/2 17:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CacheEntity {

    private Integer id;

    private String name;

    private Integer age;
}
