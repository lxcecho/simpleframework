package com.xc.joy.offer.expand.pattern.factory.method;

import com.xc.joy.offer.expand.pattern.factory.entity.DellMouse;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
