package com.xc.joy.learn.annotation.configures.conditional;

import com.xc.joy.learn.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
@Configuration
public class MyConfig {

    @Conditional(WinCondition.class)
    @Bean
    public Person mic() {
        System.out.println("将对象Mic添加到IoC容器中");
        return new Person("Mic", 16);
    }

    @Conditional(WinCondition.class)
    @Bean
    public Person tom() {
        System.out.println("将对象Tom添加到IoC容器中");
        return new Person("Tom", 18);
    }

    @Conditional(LinuxCondition.class)
    @Bean
    public Person james() {
        System.out.println("将对象James添加到IoC容器中");
        return new Person("James", 17);
    }
}
