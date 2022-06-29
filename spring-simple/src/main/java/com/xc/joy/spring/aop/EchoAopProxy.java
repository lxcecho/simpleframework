package com.xc.joy.spring.aop;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:56 29-06-2022
 */
public interface EchoAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);

}
