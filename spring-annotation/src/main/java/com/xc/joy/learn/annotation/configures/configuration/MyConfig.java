package com.xc.joy.learn.annotation.configures.configuration;

import com.xc.joy.learn.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
@Configuration
public class MyConfig {

    @Bean
    public Person person1(){
        return new Person("Tom",18);
    }
}
