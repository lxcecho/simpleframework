package com.xc.joy.expand.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public class DefendReflectAttackSingleton {

    private static DefendReflectAttackSingleton instance;

    private static boolean flag = false;

    private DefendReflectAttackSingleton() {
        synchronized (DefendReflectAttackSingleton.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("单例模式受到反射攻击！已成功阻止！");
            }
        }
    }

    public static DefendReflectAttackSingleton getInstance() {
        if (instance == null) {
            instance = new DefendReflectAttackSingleton();
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DefendReflectAttackSingleton instance = DefendReflectAttackSingleton.getInstance();
        Class<DefendReflectAttackSingleton> clazz = DefendReflectAttackSingleton.class;
        Constructor<DefendReflectAttackSingleton> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        DefendReflectAttackSingleton newInstance = constructor.newInstance();
        System.out.println(instance == newInstance);
    }
}
