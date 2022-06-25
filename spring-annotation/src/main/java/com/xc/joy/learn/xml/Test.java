package com.xc.joy.learn.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:14 25-06-2022
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
        Object bean = app.getBean("person");
        System.out.println(bean);
    }
}
