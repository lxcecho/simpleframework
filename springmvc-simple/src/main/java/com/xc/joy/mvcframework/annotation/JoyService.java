package com.xc.joy.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author lxcecho
 * @since 2020/12/31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JoyService {
    String value() default "";
}
