package com.xc.joy.learn.annotation.configures.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object bean1 = app.getBean("person");
        System.out.println(bean1);

        Object bean2 = app.getBean("person");
        System.out.println(bean2);

        System.out.println(bean1 == bean2);

    }
}
