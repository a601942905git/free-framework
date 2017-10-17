package com.free.framework.plateform.csrf.annotation;

import java.lang.annotation.*;

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
