package com.xc.joy.offer.expand.pattern.factory.method;

import com.xc.joy.offer.expand.pattern.factory.entity.LenovoMouse;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public class LenovoMouseFactory  implements MouseFactory{
    @Override
    public Mouse createMouse() {
        return new LenovoMouse();
    }
}
