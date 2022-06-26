package com.xc.joy.learn;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author lxcecho 909231497@qq.com
 * @since 10:41 26-06-2022
 *
 * ApplicationContextAware 可以通过这个上下文环境对象得到 Spring 容器中的 Bean
 */
public class MyApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
