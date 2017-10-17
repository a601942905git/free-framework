package com.free.framework.plateform.csrf.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateToken {

    /**
     * 是否验证token
     * @return
     */
    boolean vlidate() default true;
}
