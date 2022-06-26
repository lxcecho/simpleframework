package com.xc.joy.learn.annotation.configures.component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:16 25-06-2022
 */
@Configuration // 把一个类作为一个 IoC 容器，他的某个方法头上如果注册了 Bean，就会作为这个 Spring 容器中的 Bean
@ComponentScan(value = "com.xc.joy.learn",
//        includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Component.class})},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})},
        useDefaultFilters = false) // 注解默认扫描该类所在的包下所有配置类
public class MyComponentConfig {

}
