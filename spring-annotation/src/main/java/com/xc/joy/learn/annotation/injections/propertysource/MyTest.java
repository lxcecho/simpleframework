package com.xc.joy.learn.annotation.injections.propertysource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);

        System.out.println(app.getBean("bird"));

        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(", ", "\n"));

        Environment evn = app.getEnvironment();

        System.out.println("从环境变量中取值================" + evn.getProperty("bird.color"));
    }
}
