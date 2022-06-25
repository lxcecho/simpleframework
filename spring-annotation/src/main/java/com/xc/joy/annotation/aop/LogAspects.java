package com.xc.joy.annotation.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Aspect:告诉Spring这个是一个切面类。
 */
@Aspect
public class LogAspects {

	//抽取切入点表达式
	@Pointcut("execution(* com.xc.joy.annotation.aop.MathCaculator.*(..))")
	public void pointcut() {};
	
	@Before("pointcut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName()+"運行...@Before...參數列表是{"+Arrays.asList(args)+"}...");
	}
	
	@After("pointcut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName()+"结束...@After...");
	}
	
	/**
	 * JoinPoint一定要出现在参数表的第一位，否则报错。
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value="pointcut()",returning="result")
	public void logReturning(JoinPoint joinPoint,Object result) {
		System.out.println(joinPoint.getSignature().getName()+"正常返回...@AfterReturning...运行结果是{"+result+"}");	
	}
	
	@AfterThrowing(value="pointcut()",throwing="ex")
	public void logException(JoinPoint joinPoint,Exception ex) {
		System.out.println(joinPoint.getSignature().getName()+"出现异常...@AfterThrowing...异常信息{"+ex+"}");
	}
}
