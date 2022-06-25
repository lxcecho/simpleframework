package com.xc.joy.expand.annotation;

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
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseInfoAnnotation {

    public String courseName();

    public String courseTag();

    public String courseProfile();

    public int courseIndex() default 3030;

}
