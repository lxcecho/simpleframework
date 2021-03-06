package com.xc.joy.offer.simpleframework.core;

import com.xc.joy.offer.simpleframework.core.annotation.Component;
import com.xc.joy.offer.simpleframework.core.annotation.Controller;
import com.xc.joy.offer.simpleframework.core.annotation.Repository;
import com.xc.joy.offer.simpleframework.core.annotation.Service;
import com.xc.joy.offer.simpleframework.util.ClassUtil;
import com.xc.joy.offer.simpleframework.util.ValidationUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2021/1/3
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    // 存放所有被配置标记的目标对象的Map
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    // 加载bean的注解列表
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(
            Component.class, Controller.class, Service.class, Repository.class
    );

    /**
     * 获取Bean容器实例
     *
     * @return
     */
    public static BeanContainer getInstance() {
        return Container.HOLDER.instance;
    }

    private enum Container {
        HOLDER;
        private BeanContainer instance;

        Container() {
            instance = new BeanContainer();
        }
    }

    // 容器是否已经加载过bean
    private boolean loaded = false;

    /**
     * 是否已经加载过Bean
     *
     * @return 是否已加载
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Bean 实例数量
     *
     * @return 数量
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 扫描加载所有的Bean
     *
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName) {
        // 判断bean容器是否被加载过
        if (isLoaded()) {
            log.warn("extract nothing from packageName : {}", packageName);
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName : {}", packageName);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotaion : BEAN_ANNOTATION) {
                // 如果类上面标记了定义的注释
                if (clazz.isAnnotationPresent(annotaion)) {
                    // 将目标类本身作为键，目标类的实例作为值，放入到beanMap中
//                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                    addBean(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 添加一个class对象及其bean实例
     *
     * @param clazz Class对象
     * @param bean  Bean实例
     * @return 原有的Bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     * @return 删除的Bean实例，没有则返回null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean的集合
     *
     * @return Bean集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解帅选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 1 获取beanMap的所有Class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap : {}", keySet);
            return null;
        }
        // 2 通过注解筛选被注解标记的Class对象，并添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            // 类是否有相关的注解标记
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 通过接口或者父类 获取 实现类或者子类的Class集合，不包括其本身
     *
     * @param interfaceOrClass 接口Class 或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 1 获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap : {}", keySet);
            return null;
        }
        // 2 判断keySet里的元素是否是传入的接口或者类的子类
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

}
