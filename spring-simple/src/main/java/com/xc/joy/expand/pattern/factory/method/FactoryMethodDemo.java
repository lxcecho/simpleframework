package com.xc.joy.expand.pattern.factory.method;

import com.xc.joy.expand.pattern.factory.entity.Mouse;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        MouseFactory mf = new HpMouseFactory();
        Mouse mouse = mf.createMouse();
        mouse.sayHi();
    }
}
