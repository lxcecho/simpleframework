package com.xc.joy.annotation.config;

import com.xc.joy.annotation.bean.Color;
import com.xc.joy.annotation.bean.ColorFactoryBean;
import com.xc.joy.annotation.bean.Person;
import com.xc.joy.annotation.bean.Red;
import com.xc.joy.annotation.condition.LinuxCondition;
import com.xc.joy.annotation.condition.MyImportBeanDefinitionRegistrar;
import com.xc.joy.annotation.condition.MyImportSelector;
import com.xc.joy.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Configuration
//类中组件同意设置。满足当前条件，这个类中配置的所有bean注册才能生效。
@Conditional({WindowsCondition.class})
//@Import导入组件，id默认是组件的全类名。
//@Import(Color.class)//导入单个
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})//导入多个
public class MyConfig2 {

	/**
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE——prototype
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON——singleton
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST——request
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION——session
	 * @return
	 * 
	 * @Scope:调整作用域。
	 * 	prototype：原生类型，多实例的。IOC容器启动并不会去调用方法创建对象放在容器中，
	 * 		 每次获取的时候才会调用方法创建对象。
	 * 	singleton：单实例的（默认值），IOC容器启动会调用方法创建对象放到IOC容器中。
	 * 		   以后每次获取就是直接从容器（(map.get())）中拿。
	 *  request：同一次请求创建一个实例。
	 *  session：同一个session创建一个实例。
	 *  
	 * 懒加载：
	 * 	单实例bean，默认在容器启动的时候创建对象。
	 * 	懒加载：容器启动不创建对象。第一次使用（获取）bean创建对象，并初始化。
	 */
//	@Scope("prototype")
	@Lazy
	@Bean("person")
	public Person person() {
		System.out.println("给容器中添加Person。。。");
		return new Person("张三",22);
	}
	
	/**
	 * Conditional({Condition})：按照一定的条件进行判断，满足条件给容器中注册bean。
	 * 可以放在方法上，也可以放在类上。
	 * 
	 * 如果系统是Windows系统，给容器中注册“bill”；
	 * 如果是Linux系统，给容器中注册“linus”。
	 * 
	 */
	@Conditional({WindowsCondition.class})
	@Bean("bill")
	public Person person0() {
		return new Person("bill",64);
	}
	
	@Conditional({LinuxCondition.class})
	@Bean("linus") 
	public Person person1() {
		return new Person("linus",48);
	}
	
	/**
	 * 给容器中注册组件：
	 * 1）包扫描+组件标注注解（@Controller/@Service/@Repository/@Component[自己]）
	 * 2）@Bean[导入的第三方包里面的组件]
	 * 3）@Import[快速给容器中导入一个组件]
	 * 	1.@Import（要导入的组件）：容器中就会自动注册这个组件，id默认是全类名。
	 * 	2.ImportSelector：返回需要导入的组件的全类名数组。***
	 * 	3.ImportBeanDefinitionRegistrar：手动注册bean到容器中。
	 * 4）使用Spring提供的 FactoryBean （工厂Bean）.
	 * 	1.默认获取到的是工厂Bean调用getObject()创建的对象；
	 * 	2.需要获取工厂Bean本身，我们需要给id前面加一个标识符&
	 * 		&colorFactoryBean
	 */
	
	@Bean
	public ColorFactoryBean colorFactoryBean() {
		return new ColorFactoryBean();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
