package com.xc.justforjoy.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.xc.justforjoy.annotation.bean.Person;

//配置类==配置文件
@Configuration // 告诉SPring这是一配置类
/*
 * @ComponentScan 
 * 	value:指定要扫描的包；
 *  Filter[] includeFilter:指定扫描的时候只需要包含哪些组件。
 *  Filter[] excludeFilters:指定扫描的时候按照什么规则排除哪些组件。
 *  
 * FilterType指定过滤规则
 * 	FilterType.ANNOTATION:按照注解；
 * 	FilterType.ASSIGNABLE_TYPE：按照给定的类型；
 * 	FilterType.ASPECTJ:使用Aspect表达式；
 * 	FilterType.REGEX：使用正则表达式指定；
 * 	FilterType.CUSTOM：使用自定义规则
 */
/*@ComponentScan(value = "com.xc.justforjoy.annotation", includeFilters = {@Filter(type = FilterType.ANNOTATION,
				classes = { Repository.class, Controller.class})// 按照注解类型排除;classes=Controller.class
},useDefaultFilters=false)*/
//或者使用 @ComponentScans
@ComponentScans(value= {
		@ComponentScan(value = "com.xc.justforjoy.annotation", includeFilters = {
				/*@Filter(type = FilterType.ANNOTATION, 
				// 按照注解类型排除;classes=Controller.class
				classes = { Repository.class, Controller.class}),
				@Filter(type = FilterType.ASSIGNABLE_TYPE,classes=BookService.class)*/
				@Filter(type = FilterType.CUSTOM,classes=MyTypeFilter.class)
},useDefaultFilters=false)
})
public class MyConfig {

	/*
	 * 给容器中注册一个bean；类型为返回值的类型，id默认是用方法名作为id。 即配置文件中<bean>中的属性class和id
	 */
	@Bean("person") // 指定bean的名字
	public Person person01() {
		return new Person("kleine", 22);
	}

}
