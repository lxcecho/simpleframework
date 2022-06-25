package com.xc.joy.annotation.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
	
	public Car() {
		System.out.println("Car...Contructor...");
	}
	
	public void init() {
		System.out.println("Car...init...");
	}
	
	public void destroy() {
		System.out.println("Car...destroy...");
	}
	
}
