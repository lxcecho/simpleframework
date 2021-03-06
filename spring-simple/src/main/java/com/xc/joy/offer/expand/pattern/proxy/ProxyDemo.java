package com.xc.joy.offer.expand.pattern.proxy;

import com.xc.joy.offer.expand.pattern.proxy.cglib.AlipayMethodInterceptor;
import com.xc.joy.offer.expand.pattern.proxy.cglib.CglibUtil;
import com.xc.joy.offer.expand.pattern.proxy.impl.CommonPayment;
import com.xc.joy.offer.expand.pattern.proxy.impl.ToCPaymentImpl;
import net.sf.cglib.proxy.MethodInterceptor;

public class ProxyDemo {
    public static void main(String[] args) {
//        ToCPayment toCProxy = new AlipayToC(new ToCPaymentImpl());
//        toCProxy.pay();
//        ToBPayment toBProxy = new AlipayToB(new ToBPaymentImpl());
//        toBProxy.pay();
//        ToCPayment toCPayment = new ToCPaymentImpl();
//        InvocationHandler handler = new AlipayInvocationHandler(toCPayment);
//        ToCPayment toCProxy = JdkDynamicProxyUtil.newProxyInstance(toCPayment,handler);
//        toCProxy.pay();
//        ToBPayment toBPayment = new ToBPaymentImpl();
//        InvocationHandler handlerToB = new AlipayInvocationHandler(toBPayment);
//        ToBPayment toBProxy = JdkDynamicProxyUtil.newProxyInstance(toBPayment, handlerToB);
//        toBProxy.pay();
        CommonPayment commonPayment = new CommonPayment();
//        AlipayInvocationHandler invocationHandler = new AlipayInvocationHandler(commonPayment);
//        CommonPayment commonPaymentProxy = JdkDynamicProxyUtil.newProxyInstance(commonPayment, invocationHandler);
        MethodInterceptor methodInterceptor = new AlipayMethodInterceptor();
        CommonPayment commonPaymentProxy = CglibUtil.createProxy(commonPayment, methodInterceptor);
        commonPaymentProxy.pay();
        ToCPayment toCPayment = new ToCPaymentImpl();
        ToCPayment toCProxy = CglibUtil.createProxy(toCPayment, methodInterceptor);
        toCProxy.pay();
    }
}
