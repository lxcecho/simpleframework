package com.xc.joy.learn.annotation.configures.component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:16 25-06-2022
 */
@Configuration
@ComponentScan(value = "com.xc.joy",
//        includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Component.class})},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})},
        useDefaultFilters = false)
public class MyComponentConfig {

}
