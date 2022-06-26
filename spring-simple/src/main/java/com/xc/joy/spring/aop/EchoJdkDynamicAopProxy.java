package com.xc.joy.spring.aop;

import com.xc.joy.spring.aop.aspect.EchoAdvice;
import com.xc.joy.spring.aop.support.EchoAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by Tom.
 */
public class EchoJdkDynamicAopProxy implements InvocationHandler {
    private EchoAdvisedSupport config;

    public EchoJdkDynamicAopProxy(EchoAdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, EchoAdvice> advices = config.getAdvices(method, null);

        Object returnValue;
        try {
            invokeAdivce(advices.get("before"));

            returnValue = method.invoke(this.config.getTarget(), args);

            invokeAdivce(advices.get("after"));
        } catch (Exception e) {
            invokeAdivce(advices.get("afterThrow"));
            throw e;
        }

        return returnValue;
    }

    private void invokeAdivce(EchoAdvice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.config.getTargetClass().getInterfaces(), this);
    }
}
