package com.xc.joy.offer.expand.generic;

public interface GenericIFactory<T,N> {
    T nextObject();
    N nextNumber();
}
