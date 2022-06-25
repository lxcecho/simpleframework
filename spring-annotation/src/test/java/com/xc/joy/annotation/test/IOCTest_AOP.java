package com.xc.joy.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xc.joy.annotation.aop.MathCaculator;
import com.xc.joy.annotation.config.MyConfigOfAOP;

public class IOCTest_AOP {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		AnnotationConfigApplicationContext atx = 
				new AnnotationConfigApplicationContext(MyConfigOfAOP.class);
		//1.不要自己创建对象
		/*MathCaculator mathCaculator = new MathCaculator();
		mathCaculator.div(1, 1);*/
		
		MathCaculator bean = atx.getBean(MathCaculator.class);
		bean.div(1, 0);
		
//		System.out.println(atx);
		
		atx.close();
	}
	
}
