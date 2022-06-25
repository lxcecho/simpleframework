package com.xc.justforjoy.annotation.tx;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 声明式事务：
 * 环境搭建：
 * 	1.导入相关依赖
 * 		数据源、数据库驱动、Spring-jdbc模块
 * 	2.配置数据源、JdbcTemplate(Spring提供的简化数据库操作的工具）操作数据；
 * 	3.给方法上标注@Transactional 表示当前方法是一个事务方法；
 * 	4.@EnableTransactionManagement 开启基于注解的事务管理系统；
 * 		@EnableXXX
 * 	5.配置事务管理器DataSourceTransactionManager 来管理事务;
 * 		@Bean
 * 		public DataSourceTransactionManager dataSourceTransactionManager(){}
 * 	【NoSuchBeanDefinitionException(如果没有配置DataSourceTransactionManager的话)】
 * 
 * 原理：
 * 1.@EnableTransactionManagement 利用
 * 	 TransactionManagementConfigurationSelector 给容器中打入组件；
 * 		导入两个组件：
 * 		AutoProxyRegistrar：
 * 		ProxyTransactionManagementConfiguration：
 * 
 * 2.AutoProxyRegistrar：
 * 	给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；
 * 	InfrastructureAdvisorAutoProxyCreator：？
 * 	利用后置处理器机制在对象创建以后，包装对象，返回找一个代理对象（增强器），
 * 	代理对象执行方法利用拦截器链进行调用；
 * 
 * 3.ProxyTransactionManagementConfiguration 做了什么？
 * 	1）给容器中注册事务增强器；
 * 		(1)事务增强器要用事务注解的信息，AnnotationTransactionAttrubuteSource 解析事务注解；
 * 		(2)事务拦截器；
 * 			TransactionInterceptor:保存了事务属性信息，事务管理器；
 * 			他是一个MethodInterceptor；
 * 			在目标方法执行的时候：
 * 				执行拦截器链；
 * 				事务拦截器：
 * 					①先获取事务相关属性；
 * 					②再获取 PlatformTransactionManager ，如果事先没有添加指定
 * 					   任何tansactionmanager，最终会从容器中按照类型获取一个PlatformTransactionManager；
 * 					③执行目标方法：
 * 						如果异常，获取到事务管理器，利用事务管理回滚操作；
 * 						如果正常，利用事务管理器，提交事务。
 * 
 * @author kleine
 *
 */
@EnableTransactionManagement
@ComponentScan("com.xc.justforjoy.annotation.tx")
@Configuration
public class MyConfigOfTX {

	//找数据源
	@Bean
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("lixc0426");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() throws Exception {
		//Spring对@Configuration类会特殊处理，给容器中加组件的方法，多次调用都是从容器中找组件。
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
		
		return new DataSourceTransactionManager(dataSource());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
