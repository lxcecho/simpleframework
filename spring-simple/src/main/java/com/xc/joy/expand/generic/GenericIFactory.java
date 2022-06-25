package com.xc.joy.expand.generic;

public interface GenericIFactory<T,N> {
    T nextObject();
    N nextNumber();
}
