package com.xc.joy.expand.annotation;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2020/11/21
 * <p>
 * 描述：
 */
public class TypeParameterDemo<@TypeParameterAnnotation T> {
    public <@TypeParameterAnnotation U> T foo(T t) {
        return null;
    }
}
