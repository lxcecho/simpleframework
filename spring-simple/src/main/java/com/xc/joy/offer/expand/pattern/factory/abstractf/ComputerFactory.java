package com.xc.joy.offer.expand.pattern.factory.abstractf;

import com.xc.joy.offer.expand.pattern.factory.entity.Keyboard;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public interface ComputerFactory {
    Mouse createMouse();
    Keyboard createKeyboard();
}
