package com.xc.joy.offer.expand.pattern.proxy.impl;

import com.xc.joy.offer.expand.pattern.proxy.ToBPayment;

public class ToBPaymentImpl implements ToBPayment {
    @Override
    public void pay() {
        System.out.println("以公司的名义进行支付");
    }
}
