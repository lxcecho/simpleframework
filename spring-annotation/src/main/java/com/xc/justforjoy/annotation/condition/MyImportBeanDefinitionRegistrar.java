package com.xc.justforjoy.annotation.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.xc.justforjoy.annotation.bean.RainBow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * AnnotationMetadata:当前类的注释信息。
	 * BeanDefinitionRegistry:BeanDefinition注册类，
	 * 	把所有需要添加到容器中的bean，调用
	 * 	BeanDefinitionRegistry.registerBeanDefinitions手工注册进来。
	 * 
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		boolean containsBeanDefinition = registry.containsBeanDefinition("com.xc.justforjoy.annotation.bean.Red");
		boolean containsBeanDefinition2 = registry.containsBeanDefinition("com.xc.justforjoy.annotation.bean.Blue");
		if(containsBeanDefinition && containsBeanDefinition) {
			//指定bean名
			RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
			registry.registerBeanDefinition("rainBow", beanDefinition);
		}
	}

}
