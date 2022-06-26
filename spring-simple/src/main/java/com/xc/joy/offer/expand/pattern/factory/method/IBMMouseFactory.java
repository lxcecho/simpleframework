package com.xc.joy.offer.expand.pattern.factory.method;

import com.xc.joy.offer.expand.pattern.factory.entity.IBMMouse;
import com.xc.joy.offer.expand.pattern.factory.entity.Mouse;

public class IBMMouseFactory extends LenovoMouseFactory {
    @Override
    public Mouse createMouse(){
        return new IBMMouse();
    }
}
