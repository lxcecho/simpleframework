package com.xc.joy.spring.aop.support;

import com.xc.joy.spring.aop.aspect.EchoAdvice;
import com.xc.joy.spring.aop.config.EchoAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:42 26-06-2022
 *
 * 解析AOP配置的工具类
 */
public class EchoAdvisedSupport {
    private EchoAopConfig config;
    private Object target;
    private Class<?> targetClass;
    private Pattern pointCutClassPattern;

    private Map<Method, Map<String, EchoAdvice>> methodCache;

    public EchoAdvisedSupport(EchoAopConfig config) {
        this.config = config;
    }

    /**
     * 解析配置文件的方法
     */
    private void parse() {

        // 把 Spring 的 Express 变成 Java 能够识别的正则表达式
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");


        // 保存专门匹配 Class 的正则
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));


        // 享元的共享池
        methodCache = new HashMap<Method, Map<String, EchoAdvice>>();
        // 保存专门匹配方法的正则
        Pattern pointCutPattern = Pattern.compile(pointCut);
        try {
            Class aspectClass = Class.forName(this.config.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<String, Method>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }

            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                Matcher matcher = pointCutPattern.matcher(methodString);
                if (matcher.matches()) {
                    Map<String, EchoAdvice> advices = new HashMap<String, EchoAdvice>();

                    if (!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))) {
                        advices.put("before", new EchoAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectBefore())));
                    }
                    if (!(null == config.getAspectAfter() || "".equals(config.getAspectAfter()))) {
                        advices.put("after", new EchoAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfter())));
                    }
                    if (!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow()))) {
                        EchoAdvice advice = new EchoAdvice(aspectClass.newInstance(), aspectMethods.get(config.getAspectAfterThrow()));
                        advice.setThrowName(config.getAspectAfterThrowingName());
                        advices.put("afterThrow", advice);
                    }

                    // 跟目标代理类的业务方法和 Advices 建立一对多个关联关系，以便在 Proxy 类中获得
                    methodCache.put(method, advices);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据一个目标代理类的方法，获得其对应的通知
     *
     * @param method
     * @param targetClass
     * @return
     * @throws Exception
     */
    public Map<String, EchoAdvice> getAdvices(Method method,  Class<?> targetClass) throws Exception {
        // 享元设计模式的应用
        Map<String, EchoAdvice> cache = methodCache.get(method);
        if (null == cache) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache = methodCache.get(m);
            // 底层逻辑，对代理方法进行一个兼容处理
            this.methodCache.put(m, cache);
        }
        return cache;
    }

    /**
     * 给 ApplicationContext 首先 IoC 中的对象初始化时调用，决定要不要生成代理类的逻辑
     *
     * @return
     */
    public boolean pointCutMath() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
