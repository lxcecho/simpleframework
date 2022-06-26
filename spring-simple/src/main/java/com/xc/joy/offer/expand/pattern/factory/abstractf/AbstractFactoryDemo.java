package com.xc.joy.offer.expand.pattern.factory.abstractf;

import com.xc.joy.offer.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        ComputerFactory cf = new HpComputerFactory();
        Mouse mouse = cf.createMouse();
        Keyboard keyboard = cf.createKeyboard();
        mouse.sayHi();
        keyboard.sayHello();
    }
}
