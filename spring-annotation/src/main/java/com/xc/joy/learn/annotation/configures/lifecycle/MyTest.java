package com.xc.joy.learn.annotation.configures.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成");
//        System.out.println(app.getBean("car"));
        System.out.println(app.getBean("train"));

        System.out.println(app.getBean("airPlane"));
        app.close();
    }

}
