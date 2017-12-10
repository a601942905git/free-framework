package com.free.framework.plateform.csrf.annotation;

import java.lang.annotation.*;

/**
 * com.free.framework.plateform.csrf.annotation.GenerateToken
 * 生成csrf token注解
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenerateToken {

    /**
     * 是否生成token
     *
     * @return
     */
    boolean generate() default true;
}
