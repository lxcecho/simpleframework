package com.xc.joy.expand.pattern.factory.abstractf;

import com.xc.joy.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.expand.pattern.factory.entity.Mouse;

public interface ComputerFactory {
    Mouse createMouse();
    Keyboard createKeyboard();
}
