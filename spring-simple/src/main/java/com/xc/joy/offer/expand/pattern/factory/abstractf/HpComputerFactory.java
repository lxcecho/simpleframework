package com.xc.joy.offer.expand.pattern.factory.abstractf;

import com.xc.joy.offer.expand.pattern.factory.entity.HpKeyboard;
import com.xc.joy.offer.expand.pattern.factory.entity.HpMouse;
import com.xc.joy.offer.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public class HpComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}
