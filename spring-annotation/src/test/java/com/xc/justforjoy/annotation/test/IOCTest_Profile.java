package com.xc.justforjoy.annotation.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.justforjoy.annotation.bean.Yellow;
import com.xc.justforjoy.annotation.config.MyConfigOfProfile;

public class IOCTest_Profile {

	/**
	 * 1.使用命令行动态参数：在虚拟机参数位置加载 -Dspring.profiles.active=test
	 * 2.使用代码的方式激活某种环境【即使用无参构造器】；
	 */
	@Test
	public void test() {
		/*AnnotationConfigApplicationContext atx = 
				new AnnotationConfigApplicationContext(MyConfigOfProfile.class);*/
		//1.创建一个applicationContext，无参构造实例
		AnnotationConfigApplicationContext atx = 
				new AnnotationConfigApplicationContext();
		//2.设置需要激活的环境
		atx.getEnvironment().setActiveProfiles("dev");
		//3.注册主配置类
		atx.register(MyConfigOfProfile.class);
		//4.启动刷新容器
		atx.refresh();
		
		Yellow bean = atx.getBean(Yellow.class);
		System.out.println(bean);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String[] beanNamesForType = atx.getBeanNamesForType(DataSource.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		atx.close();
	}
	
}
