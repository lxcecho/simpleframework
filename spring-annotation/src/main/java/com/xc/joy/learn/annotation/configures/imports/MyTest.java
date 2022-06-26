package com.xc.joy.learn.annotation.configures.imports;

import com.xc.joy.learn.entity.Monkey;
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

        //通过FactoryBean注入的值
        System.out.println("============" + app.getBean("monkey").getClass());
        Object monkey1 = app.getBean("monkey");
        Object monkey2 = app.getBean("monkey");
        System.out.println("monkey1：" + monkey1 +" monkey2："+ monkey2);
        System.out.println("是否单例：" + (monkey1 == monkey2));

        //拿到构建 monkey 的 FactoryBean
        Object monkeyFactoryBean = app.getBean("&monkey");
        System.out.println("============" + monkeyFactoryBean);

        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(", ", "\n"));
    }

}
