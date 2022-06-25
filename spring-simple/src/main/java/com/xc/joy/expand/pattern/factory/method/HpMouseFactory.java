package com.xc.joy.expand.pattern.factory.method;

import com.xc.joy.expand.pattern.factory.entity.HpMouse;
import com.xc.joy.expand.pattern.factory.entity.Mouse;

public class HpMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
