package com.xc.joy.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author lxcecho 909231497@qq.com
 * @since 10:42 26-06-2022
 *
 * BeanDefinitionRegistryPostProcessor 实现了 BeanFactoryPostProcessor 接口，是 Spring 框架的 BeanDefinitionRegistry 的后置处理器，用来注册额外的 BeanDefinition
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
