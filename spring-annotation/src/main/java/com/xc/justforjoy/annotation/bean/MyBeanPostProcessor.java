package com.xc.justforjoy.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化后进行处理工作。
 * 将后置处理器加入到容器中。
 * 
 * @author kleine
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization..."+beanName+"-->"+bean);
		return bean;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization..."+beanName+"-->"+bean);
		return bean;
	}
	
}
