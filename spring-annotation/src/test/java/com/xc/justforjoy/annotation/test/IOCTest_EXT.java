package com.xc.justforjoy.annotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.justforjoy.annotation.ext.MyConfigOfEXT;

public class IOCTest_EXT {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = 
				new AnnotationConfigApplicationContext(MyConfigOfEXT.class);
		//发布事件
		atx.publishEvent(new ApplicationEvent(new String("我发布的事件...")) {
		});
		
		atx.close();
	}
	
}
