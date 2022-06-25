package com.xc.joy.expand.pattern.factory.abstractf;

import com.xc.joy.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.expand.pattern.factory.entity.LenovoKeyboard;
import com.xc.joy.expand.pattern.factory.entity.LenovoMouse;
import com.xc.joy.expand.pattern.factory.entity.Mouse;

public class LenovoComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new LenovoMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new LenovoKeyboard();
    }
}
