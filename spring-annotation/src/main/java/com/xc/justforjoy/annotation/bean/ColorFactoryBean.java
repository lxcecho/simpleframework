package com.xc.justforjoy.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

	//返回一个Color对象，这个对象会添加到容器中
	@Override
	public Color getObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ColorFactoryBean...ColorFactoryBean()...");
		return new Color();
	}
	
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}

	/**
	 * 是否单例：
	 * true：这个bean是单例的，在容器中保存一份；
	 * false：多实例的，每次获取都会创建一个新的bean
	 */
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
