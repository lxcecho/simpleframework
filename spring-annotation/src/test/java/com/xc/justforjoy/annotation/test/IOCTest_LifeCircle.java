package com.xc.justforjoy.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.justforjoy.annotation.config.MyConfig3;

public class IOCTest_LifeCircle {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(MyConfig3.class);
		System.out.println("容器创建完成。。。。");
		
		//Car bean = atx.getBean(Car.class);
		
		//关闭容器
		atx.close();
	}
	
}
