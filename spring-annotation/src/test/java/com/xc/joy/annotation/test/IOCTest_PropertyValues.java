package com.xc.joy.annotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.xc.joy.annotation.bean.Person;
import com.xc.joy.annotation.config.MyConfigOfPropertyValues;

public class IOCTest_PropertyValues {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(MyConfigOfPropertyValues.class);
		print(atx);
		System.out.println("============");
		Person bean = (Person) atx.getBean("person");
		System.out.println(bean);
		
		ConfigurableEnvironment environment = atx.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
		
		atx.close();
	}
	
	private void print(ApplicationContext atx) {
		// TODO Auto-generated method stub
		String[] beanDefinitionNames = atx.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
	
}
