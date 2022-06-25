package com.xc.joy.simpleframework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lxcecho
 * @since 2021/1/5
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    //方法参数名称
    String value() default "";
    //该参数是否是必须的
    boolean required() default true;
}
