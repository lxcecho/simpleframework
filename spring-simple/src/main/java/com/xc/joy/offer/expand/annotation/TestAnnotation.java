package com.xc.joy.offer.expand.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2020/11/21
 * <p>
 * 描述：
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface TestAnnotation {

}
