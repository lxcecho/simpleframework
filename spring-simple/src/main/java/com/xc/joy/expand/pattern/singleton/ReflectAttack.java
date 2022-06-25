package com.xc.joy.expand.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射攻击
 *  枚举类型：枚举类型在 Java 中天生就不惧怕反射和反序列化的攻击，这是由 Java 自身提供的逻辑保证。
 *  非枚举类型：防止反射攻击，可以增加一个标志变量，在构造函数中检查是否已被调用过，
 *      若已被调用过，将抛出异常，保证构造函数只被调用一次。
 */
public class ReflectAttack {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 枚举
        /*System.out.println(EnumStarvingSingleton.getInstance());
        Class clazz = EnumStarvingSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton) constructor.newInstance();
        System.out.println(enumStarvingSingleton.getInstance());
        System.out.println(EnumStarvingSingleton.getInstance() == enumStarvingSingleton.getInstance());// true*/

        // 非枚举 —— 攻击成功
        StarvingSingleton singleton = StarvingSingleton.getInstance();
        Class<StarvingSingleton> clazz = StarvingSingleton.class;
        Constructor<StarvingSingleton> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        StarvingSingleton newInstance = constructor.newInstance();
        System.out.println(newInstance == singleton);
    }

}
