package com.xc.joy.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否Linux系统
public class LinuxCondition implements Condition{

	/**
	 * ConditionContext:判断条件，能使用的上下文环境；
	 * AnnotatedTypeMetadata：注释信息。
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 是否Linux系统
		//1.获取到IOC当前使用的beanfactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//2.获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		//3.获取当前环境信息
		ConfigurableEnvironment environment = (ConfigurableEnvironment) context.getEnvironment();
		//4.获取到bean定义的注册类
		BeanDefinitionRegistry registry = context.getRegistry();
		//可以判断容器中的bean注册情况，也可以给容器中注册bean。
		boolean containsBeanDefinition = registry.containsBeanDefinition("person");
		
		String property = environment.getProperty("os.name");
		if(property.contains("Linux")) {
			return true;
		}
		return false;
	}

}
