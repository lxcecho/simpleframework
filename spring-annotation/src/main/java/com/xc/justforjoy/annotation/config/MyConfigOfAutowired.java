package com.xc.justforjoy.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xc.justforjoy.annotation.bean.Car;
import com.xc.justforjoy.annotation.bean.Color;
import com.xc.justforjoy.annotation.dao.BookDao;

/**
 * 自动装配：
 * 	Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值。
 * 
 * 1)@Autowired 自动注入：
 * 	 1.默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 * 	 2.如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找：
 * 		applicationContext.getBean("bookDao");
 * 	 3.@Qualifier("bookDao")：使用@Qualifier指定容器需要装配的组件id，而不是使用属性名。
 * 	 4.自动装配默认一定将属性赋值好，没有就会报错。可以使用@Autowired(required=false);
 * 	 5.@Primary：让Spring进行自动装配的时候，默认使用首选的bean；
 * 		也可以继续使用@QUalifier指定需要装配的bean的名字。
 *  BookService{
 *  	@Autowired
 *  	BookDao bookDao;
 *  }
 *  
 * 2)Spring 还支持使用@Resource(JSR250)和@inject(JSR330)[Java规范注解]
 *  	@Resource：可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的；
 *  			没有能支持@Primary功能，没有支持@Autowired(required=false)；
 *  	@Inject：需要导入javax.inject包，和@Autowired的功能一样，没有required=false的功能；
 *  @Autowired：Spring定义的； @Resource、@Inject都是java规范，在Spring外也能使用。
 *		AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能。
 *
 * 3）@Autowired：构造器，参数，方法，属性，都是从容器中获取参数组件的值。
 *		1.[标注在方法位置],[@Bean+方法参数]，参数从容器中获取。默认不写@Autowired效果都是一样的，都能自动装配。
 *		2.[标注在构造函数]，如果组件只有一个参数构造器，这个参数构造器的@Autowired可以省略，
 *		  参数位置的组件还是可以自动从容器中获取。
 *		3.[放在参数位置]；
 *
 * 4）自定义组件想要使用Spring容器底层的一些组件（ApplicationContext、BeanFactory、XXX）；
 *   自定义组件实现XXXAware：在创建对象的时候，会调用接口规定的方法注入相关组件，Aware。
 *   把Spring底层一些组件注入到自定义的bean中。
 *   XXXAware：功能使用XXXPostProcessor：
 *   	ApplicationContextAware==>ApplicationContextAwareProssor;
 */
@Configuration
@ComponentScan({"com.xc.justforjoy.annotation.dao", "com.xc.justforjoy.annotation.service",
        "com.xc.justforjoy.annotation.controller", "com.xc.justforjoy.annotation.bean"})
public class MyConfigOfAutowired {
	
//	@Primary
	@Bean("bookDao2")
	public BookDao bookDao2() {
		BookDao bookDao = new BookDao();
		bookDao.setLabel("2");
		return bookDao;
	}
	
	/*
	 * @Bean标注的方法创建对象的时候，方法参数的值从容器中获取。
	 */
	@Bean
	public Color color(Car car) {
		Color color = new Color();
		color.setCar(car);
		return color;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
