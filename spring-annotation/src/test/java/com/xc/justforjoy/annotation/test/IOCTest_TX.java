package com.xc.justforjoy.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.justforjoy.annotation.tx.MyConfigOfTX;
import com.xc.justforjoy.annotation.tx.UserService;

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
