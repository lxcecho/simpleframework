package com.xc.joy.annotation.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.joy.annotation.bean.Person;
import com.xc.joy.annotation.config.MyConfig;

public class Main {

	public static void main(String[] args) {
		//以配置文件形式创建bean实例
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
		Person person = ctx.getBean(Person.class);
		System.out.println(person);*/
		
		ApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig.class);
		Person person = atx.getBean(Person.class);
		System.out.println(person);
		
		//返回与给定类型（包括子类）匹配的bean名称，
		//从bean定义或在工厂bean情况下getObytttype的值来判断。
		String[] namesForType = atx.getBeanNamesForType(Person.class);
		for (String name : namesForType) {
			System.out.println(name);
		}
	}
	
}
