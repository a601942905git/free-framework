package com.free.framework.plateform.csrf.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshToken {

    /**
     * 是否刷新token
     *
     * @return
     */
    boolean refresh() default true;
}
