package com.xc.joy.offer.expand.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lxcecho
 * @since 2020/11/20
 * <p>
 * 获取成员变量并调用：
 * 1、批量的
 * Field[] getFields() 获取所有的公有字段
 * Field[] getDeclaredField[] 获取所有字段，包括：私有 受保护 默认 公有
 * 2、获取单个的
 * public Field getField(String fieldName) 获取某个"公有的"字段；
 * public Field getDeclaredField(String fieldName) 获取某个字段(可以是私有的)
 * <p>
 * 设置字段的值：
 * Field -->public void set(Object obj,Object value);
 * 参数说明:
 * obj:要设置的字段所在的对象
 * value:要为字段设置的值
 */
public class FieldCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("com.xc.joy.offer.expand.reflect.ReflectTarget");
        System.out.println("——————————————获取所有的公有字段————————————————");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("——————————————获取所有的字段(私有 受保护 默认 公有)————————————————");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        // 通过反射获取父类
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass);
        // 通过superclass使用上述方法就可以访问到父类的所有构造方法、成员变量和成员方法了！
        // 要排开Object的影响 做一个判断 superClass != Object.class

        System.out.println("——————————————获取公有的字段field并调用————————————————");
        Field field = clazz.getField("name");
        System.out.println("公有的Field name:" + field);
        ReflectTarget reflectTarget = (ReflectTarget) clazz.getConstructor().newInstance();
        field.set(reflectTarget, "lxcecho待反射一号");
        System.out.println("验证name：" + reflectTarget.name);

        System.out.println("——————————————获取单个私有字段targetInfo并调用————————————————");
        field = clazz.getDeclaredField("targetInfo");
        System.out.println(field);
        field.setAccessible(true);
        field.set(reflectTarget, "11111111111");
        System.out.println("验证信息：" + reflectTarget);
    }
}
