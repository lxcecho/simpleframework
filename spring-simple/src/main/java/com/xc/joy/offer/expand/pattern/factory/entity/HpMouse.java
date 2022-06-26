package com.xc.joy.offer.expand.pattern.factory.entity;


import com.xc.joy.offer.expand.annotation.TestAnnotation;

public class HpMouse  implements Mouse {
    @Override
    @TestAnnotation
    public void sayHi() {
        System.out.println("我是惠普鼠标");
    }
}
