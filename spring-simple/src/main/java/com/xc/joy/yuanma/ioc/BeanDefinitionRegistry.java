package com.xc.joy.yuanma.ioc;

/**
 * @author lxcecho 909231497@qq.com
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws Exception;
}
