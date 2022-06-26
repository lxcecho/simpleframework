package com.xc.joy.offer.expand.pattern.singleton;

import java.io.Serializable;

public class StarvingSingleton implements Serializable {

    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

    private StarvingSingleton() {
    }

    public static StarvingSingleton getInstance() {
        return starvingSingleton;
    }

}

