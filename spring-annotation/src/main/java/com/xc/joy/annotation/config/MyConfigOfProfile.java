package com.xc.joy.annotation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xc.joy.annotation.bean.Yellow;

/**
 * Profile:
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能。
 * 开发环境、测试环境、生产环境：
 * 数据源：（/A）（/B）（/C）
 * 
 * @Profile：指定组件在哪个环境的情况下才能被注册到容器中；不指定，任何环境下都能注册这个组件。
 * 		1)加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是“default”环境。
 * 		2）写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才开始生效。
 * 		3）没有标注环境标识的bean在任何环境下都是加载的。
 * @author kleine
 *
 */
//@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MyConfigOfProfile implements EmbeddedValueResolverAware{

	@Value("${jdbc.user}")
	private String user;
	
	private StringValueResolver valueResolver;
	
	private String driverClass;
	
	@Bean
	public Yellow yellow() {
		return new Yellow();
	}
	
	@Profile("test")
	@Bean("tesDataSourcet")
	public DataSource dataSourceTest(@Value("${password}")String pwd) throws Exception {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		
		return dataSource;
	}
	
	@Profile("dev")
	@Bean("devDataSource")
	public DataSource dataSourceDev(@Value("${password}")String pwd) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
		
		return dataSource;
	}
	
	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource dataSourceProd(@Value("${password}")String pwd) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bs");
		
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		// TODO Auto-generated method stub
		this.valueResolver = resolver;
		driverClass = valueResolver.resolveStringValue("${driverClass}");
	}
	
}
