package com.xc.joy.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.xc.joy.annotation.aop.LogAspects;
import com.xc.joy.annotation.aop.MathCaculator;

/**
 * Spring AOP:【动态代理】
 * 		指在程序运行期间【动态】的将某段代码切入到指定方法指定位置进行运行的编程方式。
 * 1）导入AOP模块：spring-aop：spring-aspects包
 * 2）定义一个业务逻辑（MathCaculator），在业务逻辑运行的时候将日志进行打印（方法之前、
 * 	   方法运行结束、方法出现异常、XXX）
 * 3）定义一个日志切面类（LogAspects），切面类里面的方法要动态感知MathCaculator.div
 * 	   运行到哪里然后执行。
 * 		通知方法：
 * 			前置通知(@Before)：logStart();在目标方法（div）之前运行；
 * 			后置通知(@After)：logEnd();在目标方法运行结束之后运行；无论方法是正常结束还是异常结束，都会调用。
 * 			返回通知(@AfterReturning)：logReturning();在目标方法正常返回之后执行；
 * 			异常通知(@AfterThrowing)：logException();在目标方法出现异常之后运行；
 *  		环绕通知(@Around)：动态代理，手动推进目标方法运行（JoinPoint.procced()）.
 * 4）给切面类的目标方法标注何时何地运行（通知注解）;
 * 5）将切面类和业务逻辑类（目标方法所在类）都加入到容器中；
 * 6）告诉Spring哪个类是切面类，给切面类加上一个注解 @Aspect；
 * 【7】给配置类中加@EnableAspectJAutoProxy【开启基于注解的AOP模式】;
 * 		在Spring中很多的@EnableXXX；
 * 
 * 三步：
 * 	1.将业务逻辑组件和切面类加入到容器中，告诉Spring哪个是切面类（@Aspect）；
 * 	2.在切面类上每一个通知方法上标注注解，告诉Spring何时何地运行（切入点表达式Pointcut）
 * 	3.开启基于注解的AOP模式（@EnableAspectJAutoProxy）。
 * 
 * AOP原理：【给容器中注册了什么组件，这个组件什么时候工作，包括这个组件工作时候的功能】
 * 		@EnableAspectJAutoProxy
 * 1.@EnableAspectJAutoProxy是什么？
 * 	@Import(AspectJAutoProxyRegistrar.class)给容器中导入AspectJAutoProxyRegistrar；
 * 		利用AspectJAutoProxyRegistrar自定义给容器中注册bean；BeanDefinition
 * 		internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 * 	给容器中注册一个AnnotationAwareAspectJAutoProxyCreator。
 * 
 * 2.AnnotationAwareAspectJAutoProxyCreator：
 * 		-->AspectJAwareAdvisorAutoProxyCreator
 * 			-->AbstractAdvisorAutoProxyCreator
 * 				-->AbstractAutoProxyCreator
 * 					implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 			关注后置处理器（在bean初始化完成前后做事情），自动注入BeanFactory；
 * 
 * AbstractAutoProxyCreator.setBeanFactory()
 * 
 * AbstractAutoProxyCreator.postProcessBeforeInstantiation():有后置处理器的逻辑
 * 
 * AbstractAdvisorAutoProxyCreator.setBeanFactory()-->initBeanFactory()
 * 
 * AspectJAwareAdvisorAutoProxyCreator
 * 
 * AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 * 
 * 流程：
 * 	1）传入配置类，创建IOC容器；
 * 	2）注册配置类，调用refresh()方法刷新容器；
 * 	3）registerBeanPostProcessors(beanFactory)：注册bean的后置处理器来方便拦截bean的创建；
 * 		1.先获取IOC容器已定义了的需要创建对象的所有BeanPostProcessor；
 * 		2.给容器中添加别的BeanPostProcessor
 * 		3.优先注册实现了PriorityOrdered接口的BeanPostProcessor；
 * 		4.再给容器中注册实现Ordered接口的BeanPostProcessor；
 * 		5.注册实现优先级接口的BeanPostProcessor；
 * 		6.注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中；
 * 			创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator为例】
 * 			①创建Bean的实例；
 * 			②populateBean()给Bean各种属性赋值；
 * 			③initializeBean：初始化bean；
 * 				（1）invokeAwareMethod():处理Aware接口的方法回调；
 * 				（2）applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessorsBeforeInitialization()；
 * 				（3）invokeInitMethods()执行自定义的初始化方法；
 * 				（4）applyBeanPostProcessorsAfterInitialization()：执行后置处理器的postProcessorsAfterInitialization()；
 * 			④BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功；-->aspectJAdvisorsBuilder
 * 		7.把BeanPostProcessor注册到BeanFactory中；
 * 			BeanFactory.addBeanPostProcessor(postProcessor);
 * 
 * 	=============以上是创建AnnotationAwareAspectJAutoProxyCreator的过程==================
 * 		
 * 		AnnotationAwareAspectJAutoProxyCreator=>InstantiationAwareBeanPostProcessor
 * 	4）finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例bean；
 * 		1.遍历获取容器中所有的bean，依次创建对象；
 * 			getBean()-->doGetBean()-->getSingleton()-->
 * 		2.创建bean；
 * 		【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前都有一个拦截，InstantiationAwareBeanPostProcessor会调用postProcessBeforeInstantiation()】
 * 			①先从缓存中获取当前bean，如果能获取到说明bean是之前被创建过的，直接使用；
 * 			   否则再创建；只要创建好的bean都会被缓存起来；
 * 			②createBean():创建bean；AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例；
 * 			【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 * 			【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
 * 				（1）resolveBeforeInstantiation(beanName,mbdToUse):解析BeforeInstantiation；
 * 				    希望后置处理器在此返回一个代理对象，如果能返回代理对象就使用，如果不能就继续；
 * 					——后置处理器先尝试返回对象；
 * 						bean=applyBeanProcessorsBeforeInstantiation();
 * 						拿到所有后置处理器如果是InstantiationAwareBeanPostProcessor；
 * 						就执行后置处理器的postProcessorBeforeInstantiation
 * 						if(bean != null){
 * 							bean = applyBeanProcessorsBeforeInstantiation(bean,beanName);
 * 						}
 * 					
 * 				（2）doCreateBean(beanName,mbdToUse,args):真正的区创建一个bean实例，和3.6流程一样；
 * 
 * AnnotationAwareAspectJAutoProxyCreator【nstantiationAwareBeanPostProcessor】的作用：
 * 1）每一个bean创建之前，调用postProcessBeforeInstantiation();
 * 	    关心MathCaculator和LogAspects的创建；
 * 	1.判断当前bean时候在adviseBean中（保存了所有需要增强bean）；
 * 	2.判断当前本是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean或者是否是切面（@Aspect）；
 * 	3.是否需要跳过：
 * 		①获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
 * 		 每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor,
 * 		 判断每一个增强是否是AspectJPointcutAdvisor类型的，返回true；
 * 		②永远返回false；
 * 2）创建对象
 * 	postProcessAfterInitialization：
 * 	return wrapIfNecessary(bean,beanName,cacheKey);//包装如果需要的情况下；
 * 	1.获取当前bean的所有增强器（通知方法）；
 * 		①找到候选的所有增强器（找哪些通知方法是需要切入当前bean方法的）；
 * 		②获取到能在bean使用的增强器；
 * 		③给增强器排序；
 * 	2.保存当前bean在advisedBean中；
 * 	3.如果当前bean需要增强，创建当前bean的代理对象；
 * 		①获取所有增强（通知方法）；
 * 		②保存到ProxyFactory；
 * 		③创建代理对象，Spring自动决定;
 * 			JdkDynamicAopProxy(config);jdk动态代理；
 * 			ObjenesisCglibAopProxy(config);cglib的动态代理；
 * 	4.给容器中返回当前组件使用cglib增强了的代理对象；
 * 	5.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
 * 	
 * 3）目标方法执行：
 * 	容器中把保存了组件的代理对象（CgLib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，XXX）；
 * 	1.CglibAopProxy.intercept();拦截目标方法的执行；
 * 	2.根据ProxyFactory对象获取将要执行的目标方法拦截器链；
 * 		List<Object> chain=this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,targetClass);
 * 		①List<Obejct> interceptorList保存所有拦截器；（5个）
 * 		 一个默认的ExposeInvocationInterceptor和4个增强器；
 * 		②遍历所有的增强器，将其转为Interceptor:
 * 			registry.getInterceptors(advisor);
 * 		③将增强器转为List<MethodInterceptor>;
 * 		 如果是MethodInterceptor，直接加入到集合中；
 * 		 如果不是，使用；AdvisorAdapter将增强器转为MethodInterceptor；
 * 		 转换完成返回MethodInterceptor数组；
 * 	3.如果没有拦截器，直接执行目标方法；
 * 		拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 * 	4.如果有拦截器，把需要执行的目标方法，拦截器等信息传入创建一个CglibMethodInvocation对象，
 * 	  并调用Object retVal = mi.procced();
 * 	5.拦截器链的触发过程：
 * 		①如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法；
 * 		②链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 * 		  拦截器链的机制，保证通知方法与目标方法的执行。
 * 		
 * 总结：
 * 	1.@EnableAspectJAutoProxy 开启AOP功能；
 * 	2.@EnableAspectJAutoProxy 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator；
 * 	3.AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器；
 * 	4.容器的创建流程：
 * 		1）registerBeanPostProcessor()注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator；
 * 		2）finishBeanFactoryInitialization()初始化剩下的单实例bean；
 * 			①创建业务逻辑组件和切面组件；
 * 			②AnnotationAwareAspectJAutoProxyCreator 拦截器组件的创建过程；
 * 			③组件创建完成后，判断组件是否需要增强：
 * 				是：切面的通知方法，包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象（Cglib);
 * 	5.执行目标方法：
 * 		1）代理对象执行目标方法；
 * 		2）CglibAopProxy.intercept();
 * 			①得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor);
 * 			②利用拦截器的链式机制，依次进入每一个拦截器进行执行；
 * 			③效果：
 * 				正常执行：前置通知-->目标方法-->后置通知-->返回通知
 * 				出现异常：前置通知-->目标方法-->后置通知-->异常通知
 * 
 * @author kleine
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MyConfigOfAOP {

	//将业务逻辑类加到容器中
	@Bean
	public MathCaculator caculator() {
		return new MathCaculator();
	}
	
	//切面类加入到容器中
	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
}
