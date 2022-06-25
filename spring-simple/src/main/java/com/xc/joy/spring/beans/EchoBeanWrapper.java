package com.xc.joy.spring.beans;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:49 25-06-2022
 */
public class EchoBeanWrapper {
    private Object wrapperInstance;
    private Class<?> wrappedClass;

    public EchoBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
