package com.xc.joy.offer.expand.pattern.singleton;

import java.lang.reflect.InvocationTargetException;

public class LazyDoubleCheckSingleton {

    // volatile 防止指令重排 即 按照下面的三个顺序执行
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        //第一次检测
        if (instance == null) {
            //同步
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    // memory = allocate(); //1.分配对象内存空间
                    // instance(memory);    //2.初始化对象
                    // instance = memory;   //3.设置instance指向刚分配的内存地址，此时instance！=null
                    // 2 和 3 可能会发生顺序颠倒 即 指令重排
                    instance = new LazyDoubleCheckSingleton();
                }

            }
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        System.out.println(LazyDoubleCheckSingleton.getInstance());
    }
}

