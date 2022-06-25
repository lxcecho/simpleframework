package com.xc.joy.learn.annotation.configures.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {

        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成");
        app.getBean("person");
    }
}
