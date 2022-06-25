package com.xc.joy.expand.pattern.factory.abstractf;

import com.xc.joy.expand.pattern.factory.entity.DellKeyboard;
import com.xc.joy.expand.pattern.factory.entity.DellMouse;
import com.xc.joy.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.expand.pattern.factory.entity.Mouse;

public class DellComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
