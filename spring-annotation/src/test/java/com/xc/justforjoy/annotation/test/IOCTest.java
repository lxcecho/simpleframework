package com.xc.justforjoy.annotation.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.xc.justforjoy.annotation.bean.Blue;
import com.xc.justforjoy.annotation.bean.Person;
import com.xc.justforjoy.annotation.config.MyConfig;
import com.xc.justforjoy.annotation.config.MyConfig2;

public class IOCTest {

	@Test
	public void testImport() {
		ApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig2.class);
		print(atx);
		Blue bean = atx.getBean(Blue.class);
		System.out.println(bean);
		
		//工厂Bean获取的是调用getObject()创建的对象。
		Object colorFactoryBean = atx.getBean("colorFactoryBean");
		Object colorFactoryBean2 = atx.getBean("colorFactoryBean");
		System.out.println("Bean的类型"+colorFactoryBean.getClass());
		System.out.println(colorFactoryBean == colorFactoryBean2);
		
		//拿到工厂Bean的本身
		Object colorFactoryBean3 = atx.getBean("&colorFactoryBean");
		System.out.println(colorFactoryBean3.getClass());
	}
	
	private void print(ApplicationContext atx) {
		// TODO Auto-generated method stub
		String[] beanDefinitionNames = atx.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}

	@Test
	public void test03() {
		ApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig2.class);
		
		String[] beanNamesForType = atx.getBeanNamesForType(Person.class);
		ConfigurableEnvironment environment = (ConfigurableEnvironment) atx.getEnvironment();
		//动态获取环境变量的值，Windows8.1
		String property = environment.getProperty("os.name");
		System.out.println(property);
		
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		Map<String, Person> persons = atx.getBeansOfType(Person.class);
		System.out.println(persons);
	}
	
	@Test
	public void test02() {
		ApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig2.class);
		/*String[] beanDefinitionNames = atx.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
		//默认是单例的
		//当Scope类型为prototype时，获取几次就调用几次。
		System.out.println("IOC容器创建完成。。。");
		Object person = atx.getBean(Person.class);
		Object person2 = atx.getBean(Person.class);
		System.out.println(person == person2);*/
		
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test01() {
		ApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig.class);
		String[] beanDefinitionNames = atx.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
	
}
