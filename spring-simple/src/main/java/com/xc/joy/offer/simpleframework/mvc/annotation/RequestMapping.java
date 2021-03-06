package com.xc.joy.offer.simpleframework.mvc.annotation;

import com.xc.joy.offer.simpleframework.mvc.type.RequestMethod;

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
public @interface RequestMapping {
    // 请求路径
    String value() default "";

    // 请求方法
    RequestMethod method() default RequestMethod.GET;
}
