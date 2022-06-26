package com.xc.joy.offer.expand.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2020/11/21
 * <p>
 * 描述：
 */
public class AnnotationParser {
    /**
     * 解析类的注解
     *
     * @throws ClassNotFoundException
     */
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.xc.joy.offer.expand.annotation.Course");
        // TODO 这里获取的是class对象的注解，而不是其里面的方法和成员变量的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
            System.out.println("课程名:" + courseInfoAnnotation.courseName() + "\n" +
                    "课程标签：" + courseInfoAnnotation.courseTag() + "\n" +
                    "课程简介：" + courseInfoAnnotation.courseProfile() + "\n" +
                    "课程序号：" + courseInfoAnnotation.courseIndex());
        }
    }

    /**
     * 解析成员变量上的标签注解
     *
     * @throws ClassNotFoundException
     */
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.xc.joy.offer.expand.annotation.Course");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // TODO 判断成员变量中是否有指定注解类型的注解
            boolean hasAnnotation = field.isAnnotationPresent(PersonInfoAnnotation.class);
            if (hasAnnotation) {
                PersonInfoAnnotation personInfoAnnotation = field.getAnnotation(PersonInfoAnnotation.class);
                System.out.println("名字：" + personInfoAnnotation.name() + "\n" +
                        "年龄：" + personInfoAnnotation.age() + "\n" +
                        "性别：" + personInfoAnnotation.gender() + "\n");
                for (String language : personInfoAnnotation.language()) {
                    System.out.println("开发语言：" + language);
                }
            }
        }
    }

    /**
     * 解析成员方法上的标签注解
     *
     * @throws ClassNotFoundException
     */
    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.xc.joy.offer.expand.annotation.Course");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            // TODO 判断方法中是否有指定注解类型的注解
            boolean hasAnnotation = declaredMethod.isAnnotationPresent(CourseInfoAnnotation.class);
            if (hasAnnotation) {
                CourseInfoAnnotation courseInfoAnnotation = declaredMethod.getAnnotation(CourseInfoAnnotation.class);
                System.out.println("课程名：" + courseInfoAnnotation.courseName() + "\n" +
                        "课程标签：" + courseInfoAnnotation.courseTag() + "\n" +
                        "课程简介：" + courseInfoAnnotation.courseProfile() + "\n" +
                        "课程序号：" + courseInfoAnnotation.courseIndex() + "\n");
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        parseTypeAnnotation();
        parseFieldAnnotation();
//        parseMethodAnnotation();
    }
}
