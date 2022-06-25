package com.xc.joy.expand.pattern.proxy.impl;

import com.xc.joy.expand.pattern.proxy.ToCPayment;

public class ToCPaymentImpl implements ToCPayment {
    @Override
    public void pay() {
        System.out.println("以用户的名义进行支付");
    }
}
