package com.xc.joy.learn.annotation.configures.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyComponentScanConfig.class);
        String [] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]","")
                .replaceAll(", ","\n"));
    }

}
