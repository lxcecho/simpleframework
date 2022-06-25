package com.xc.joy.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.joy.annotation.tx.MyConfigOfTX;
import com.xc.joy.annotation.tx.UserService;

public class IOCTest_TX {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = 
				new AnnotationConfigApplicationContext(MyConfigOfTX.class);
		UserService bean = atx.getBean(UserService.class);
		bean.insert();
		
		atx.close();
	}
	
}
