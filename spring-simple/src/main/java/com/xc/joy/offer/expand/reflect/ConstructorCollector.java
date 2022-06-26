package com.xc.joy.offer.expand.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lxcecho
 * @since 2020/11/20
 * <p>
 * 通过Class对象可以获取某个类中的：构造方法
 * 获取构造方法：
 * 1、批量的方法
 * public Constructor<?>[] getConstructors() 所有 ”公有的“构造方法
 * public Constructor<?>[] getDeclaredConstructors() 所有的构造方法（包括私有、受保护、默认、公有）
 * 2、获取单个的方法，并调用
 * public Constructor<T> getConstructor(Class<?>... parameterTypes) 获取单个的”公有的“构造方法
 * public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) 获取”某个构造方法“ 可以是私有的、受保护的、默认、公有
 */
public class ConstructorCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.xc.joy.offer.expand.reflect.ReflectTarget");
        System.out.println("_______________所有公有构造方法___________________");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("_______________所有构造方法___________________");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("_______________获取公有、有两个参数的构造方法___________________");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        System.out.println("constructor" + constructor);

        System.out.println("_______________获取私有构造方法___________________");
        constructor = clazz.getDeclaredConstructor(int.class);
        System.out.println("private constructor" + constructor);

        System.out.println("_______________调用私有构造方法创建实例___________________");
        // 暴力访问（忽略掉访问修饰符）
        constructor.setAccessible(true);
        ReflectTarget reflectTarget = (ReflectTarget) constructor.newInstance(1);
        System.out.println(reflectTarget);
    }
}