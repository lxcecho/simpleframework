package com.xc.justforjoy.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否Windows系统
public class WindowsCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO Auto-generated method stub
		ConfigurableEnvironment environment = (ConfigurableEnvironment) context.getEnvironment();
		String property = environment.getProperty("os.name");
//		System.out.println(property);
		if(property.contains("Windows")) {
			return true;
		}
		return false;
	}

}
