package com.xc.joy.spring.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:42 26-06-2022
 *
 * 用于通知回调
 */
@Data
public class EchoAdvice {

    private Object aspect;

    private Method adviceMethod;

    private String throwName;

    public EchoAdvice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }

}
