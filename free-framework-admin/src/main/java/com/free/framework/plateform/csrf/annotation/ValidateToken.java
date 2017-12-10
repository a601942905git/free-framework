package com.free.framework.plateform.csrf.annotation;

import java.lang.annotation.*;

/**
 * com.free.framework.plateform.csrf.annotation.ValidateToken
 * 验证csrf token
 * @author lipeng
 * @dateTime 2017/9/17 3:19
 */
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
