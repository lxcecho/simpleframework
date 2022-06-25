package com.xc.joy.expand.pattern.proxy.impl;

import com.xc.joy.expand.pattern.proxy.ToBPayment;

public class AlipayToB implements ToBPayment {
    ToBPayment toBPayment;

    public AlipayToB(ToBPayment toBPayment){
        this.toBPayment = toBPayment;
    }

    @Override
    public void pay() {
        beforePay();
        toBPayment.pay();
        afterPay();
    }

    private void beforePay() {
        System.out.println("从招行取款");
    }
    private void afterPay() {
        System.out.println("支付给慕课网");
    }
}
