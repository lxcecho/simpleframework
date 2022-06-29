package com.xc.joy.spring.context;

import com.xc.joy.spring.annotation.EchoAutowired;
import com.xc.joy.spring.annotation.EchoController;
import com.xc.joy.spring.annotation.EchoService;
import com.xc.joy.spring.aop.EchoJdkDynamicAopProxy;
import com.xc.joy.spring.aop.support.EchoAdvisedSupport;
import com.xc.joy.spring.beans.EchoBeanWrapper;
import com.xc.joy.spring.beans.config.EchoBeanDefinition;
import com.xc.joy.spring.beans.support.EchoBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:49 25-06-2022
 * <p>
 * 职责：完成 Bean 的创建和 DI
 */
public class EchoApplicationContext {

    private EchoBeanDefinitionReader reader;

    /**
     * 存储注册信息的 BeanDefinition
     */
    private Map<String, EchoBeanDefinition> beanDefinitionMap = new HashMap<String, EchoBeanDefinition>();

    /**
     * 通用的 IOC 容器
     */
    private Map<String, EchoBeanWrapper> factoryBeanInstanceCache = new HashMap<String, EchoBeanWrapper>();

    /**
     * 单例的 IOC 容器缓存
     */
    private Map<String, Object> factoryBeanObjectCache = new HashMap<String, Object>();

    public EchoApplicationContext(String... configLocations) {

        // 1、加载配置文件
        reader = new EchoBeanDefinitionReader(configLocations);

        try {
            // 2、解析配置文件，封装成 BeanDefinition
            List<EchoBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

            // 3、把 BeanDefinition 缓存起来
            doRegistyBeanDefinition(beanDefinitions);

            // 4、完成自动依赖注入
            doAutowired();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 只处理非延时加载的情况
     */
    private void doAutowired() {
        // 调用 getBean()
        // 这一步，所有的 Bean 并没有真正的实例化，还只是配置阶段
        for (Map.Entry<String, EchoBeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }

    private void doRegistyBeanDefinition(List<EchoBeanDefinition> beanDefinitions) throws Exception {
        for (EchoBeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
            beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
        }
    }

    /**
     * Bean 的实例化，DI 是从而这个方法开始的，通过读取 BeanDefinition 中的信息，然后通过反射机制创建一个实例并返回
     * Spring 的做法是：不会把最原始的对象放出去，会用一个 BeanWrapper 来进行一次包装
     * 装饰器模式：
     * 1、保留原来的 OOP 关系
     * 2、需要对他进行扩展，增强（为了以后 AOP 打基础）
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        // 1、先拿到 BeanDefinition 配置信息
        EchoBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        // 2、反射实例化 newInstance();
        Object instance = instantiateBean(beanName, beanDefinition);
        // 3、封装成一个叫做 BeanWrapper
        EchoBeanWrapper beanWrapper = new EchoBeanWrapper(instance);
        // 4、保存到 IoC 容器
        factoryBeanInstanceCache.put(beanName, beanWrapper);
        // 5、执行依赖注入
        populateBean(beanName, beanDefinition, beanWrapper);

        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, EchoBeanDefinition beanDefinition, EchoBeanWrapper beanWrapper) {
        // 可能涉及到循环依赖？
        // A{ B b}
        // B{ A b}
        // 用两个缓存，循环两次
        // 1、把第一次读取结果为空的 BeanDefinition 存到第一个缓存
        // 2、等第一次循环之后，第二次循环再检查第一次的缓存，再进行赋值

        Object instance = beanWrapper.getWrapperInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();

        // 在 Spring 中 @Component
        if (!(clazz.isAnnotationPresent(EchoController.class) || clazz.isAnnotationPresent(EchoService.class))) {
            return;
        }

        // 把所有的包括 private/protected/default/public 修饰字段都取出来
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(EchoAutowired.class)) {
                continue;
            }

            EchoAutowired autowired = field.getAnnotation(EchoAutowired.class);

            // 如果用户没有自定义的 beanName，就默认根据类型注入
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                // field.getType().getName() 获取字段的类型
                autowiredBeanName = field.getType().getName();
            }

            // 暴力访问
            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
                // ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }

    }


    /**
     * 创建真正的实例对象
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object instantiateBean(String beanName, EchoBeanDefinition beanDefinition) {
        // 1、拿到要实例化的对象的类名
        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)) {
                instance = factoryBeanObjectCache.get(beanName);
            } else {
                // 2、反射实例化，得到一个对象
                Class<?> clazz = Class.forName(className);
                // 默认的类名首字母小写
                instance = clazz.newInstance();

                // ------------AOP start----------------
                // 如果满足条件，就直接返回 Proxy 对象
                EchoAdvisedSupport config = instantiateAopConfig(beanDefinition);
                config.setTarget(instance);
                config.setTargetClass(clazz);

                // 判断规则，要不要生成代理类，如果要就覆盖原生对象，如果不要就不做任何处理
                if (config.pointCutMath()) {
                    instance = new EchoJdkDynamicAopProxy(config).getProxy();
                }
                // -------------AOP end---------------

                this.factoryBeanObjectCache.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private EchoAdvisedSupport instantiateAopConfig(EchoBeanDefinition beanDefinition) {

        return null;
    }

    public Object getBean(Class beanClass) {
        return getBean(beanClass.getName());
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[beanDefinitionMap.size()]);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
