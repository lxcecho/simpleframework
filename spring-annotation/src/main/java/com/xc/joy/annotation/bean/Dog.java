package com.xc.joy.annotation.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware{

	@Autowired
	private ApplicationContext applicationContext;
	
	public Dog() {
		System.out.println("Dog...Constructor...");
	}
	
	//对象创建并赋值之后调用
	@PostConstruct
	public void init() {
		System.out.println("Dog...@PostConstruct...");
	}
	
	//容器移除对象之前
	@PreDestroy
	public void destroy() {
		System.out.println("Dog...@PreDestroy...");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
		System.out.println("开始创建..."+applicationContext);
	}
}
