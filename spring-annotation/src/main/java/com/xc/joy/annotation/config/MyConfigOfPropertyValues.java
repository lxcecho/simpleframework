package com.xc.joy.annotation.config;

import com.xc.joy.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用PropertySource读取配置文件中的K/V保存到运行的环境变量中。
 * 加载完外部的配置文件以后使用${}取出配置文件的值。
 */
@PropertySource(value= {"classpath:person.properties"})
@Configuration
public class MyConfigOfPropertyValues {

	@Bean
	public Person person() {
		return new Person();
	}
	
}
