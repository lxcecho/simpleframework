package com.xc.joy.expand.pattern.factory.simple;

import com.xc.joy.expand.pattern.factory.entity.DellMouse;
import com.xc.joy.expand.pattern.factory.entity.HpMouse;
import com.xc.joy.expand.pattern.factory.entity.LenovoMouse;
import com.xc.joy.expand.pattern.factory.entity.Mouse;

public class MouseFactory {
    public static Mouse createMouse(int type) {
        switch (type) {
            case 0:
                return new DellMouse();
            case 1:
                return new HpMouse();
            case 2:
                return new LenovoMouse();
            default:
                return new DellMouse();
        }
    }

    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse(1);
        mouse.sayHi();
    }
}
