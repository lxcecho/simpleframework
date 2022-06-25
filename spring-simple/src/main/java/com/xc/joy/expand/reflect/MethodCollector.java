package com.xc.joy.expand.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lxcecho
 * @since 2020/11/20
 * <p>
 * 获取成员方法并调用：
 * 1、批量的
 * public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
 * public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
 * 2、获取单个的
 * public Method getMethod(String name,Class<?>... parameterTypes)
 * name：方法名
 * Class...：形参的Class类型对象
 * public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
 * <p>
 * 调用方法
 * Method-->public Object invoke(Object obj,Object... args)
 * 参数说明：
 * obj：要调用方法的对象；
 * args：调用方法时所传递的实参。
 */
public class MethodCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("com.xc.joy.expand.reflect.ReflectTarget");
        System.out.println("***************获取所有的public方法，包括父类和Object*******************");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("***************获取公有的show2()方法*******************");
        Method method = clazz.getMethod("show2", String.class);
        System.out.println(method);
        // 调用show2并执行
        ReflectTarget reflectTarget = (ReflectTarget) clazz.getConstructor().newInstance();
        method.invoke(reflectTarget, "待反射方法");

        System.out.println("***************获取私有的show4()方法******************");
        method = clazz.getDeclaredMethod("show4", int.class);
        System.out.println(method);
        method.setAccessible(true);
        String result = String.valueOf(method.invoke(reflectTarget, 20));
        System.out.println("返回值：" + result);

    }
}
