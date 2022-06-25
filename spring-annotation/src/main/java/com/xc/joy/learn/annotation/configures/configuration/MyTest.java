package com.xc.joy.learn.annotation.configures.configuration;

import com.xc.joy.learn.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object bean = app.getBean("person");
        System.out.println(bean);

        String [] beanNames = app.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNames));
    }

}
