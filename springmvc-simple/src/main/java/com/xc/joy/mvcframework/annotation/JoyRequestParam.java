package com.xc.joy.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author lxcecho
 * @since 2020/12/31
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JoyRequestParam {
    String value() default "";
}
