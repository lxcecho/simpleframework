package com.xc.joy.annotation.test;

import com.xc.joy.annotation.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.joy.annotation.bean.Boss;
import com.xc.joy.annotation.bean.Car;
import com.xc.joy.annotation.bean.Color;
import com.xc.joy.annotation.config.MyConfigOfAutowired;

public class IOCTest_Autowired {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(MyConfigOfAutowired.class);
		BookService bookService = atx.getBean(BookService.class);
		System.out.println(bookService.toString());
		
//		BookDao bean = atx.getBean(BookDao.class);
//		System.out.println(bean);
		
		Boss boss = atx.getBean(Boss.class);
		System.out.println(boss);
		Car car = atx.getBean(Car.class);
		System.out.println(car);
		
		Color color = atx.getBean(Color.class);
		System.out.println(color);
		
		atx.close();
	}
	
}
