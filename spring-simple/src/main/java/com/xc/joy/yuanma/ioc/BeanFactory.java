package com.xc.joy.yuanma.ioc;

/**
 * @author lxcecho 909231497@qq.com
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

    Object createBean(BeanDefinition beanDefinition) throws Exception;

}
