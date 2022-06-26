package com.xc.joy.spring.beans.config;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:49 25-06-2022
 */
public class EchoBeanDefinition {

    private String factoryBeanName;

    private String beanClassName;

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

}
