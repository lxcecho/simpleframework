package com.xc.joy.spring.aop;

import com.xc.joy.spring.aop.aspect.EchoAdvice;
import com.xc.joy.spring.aop.support.EchoAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:42 26-06-2022
 */
public class EchoJdkDynamicAopProxy implements EchoAopProxy, InvocationHandler {
    private EchoAdvisedSupport config;

    public EchoJdkDynamicAopProxy(EchoAdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, EchoAdvice> advices = config.getAdvices(method, this.config.getTargetClass());

        Object returnValue;
        try {
            invokeAdvice(advices.get("before"));

            returnValue = method.invoke(this.config.getTarget(), args);

            invokeAdvice(advices.get("after"));
        } catch (Exception e) {
            invokeAdvice(advices.get("afterThrow"));
            throw e;
        }

        return returnValue;
    }

    private void invokeAdvice(EchoAdvice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getProxy() {
        return getProxy(this.config.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.config.getTargetClass().getInterfaces(), this);
    }
}
