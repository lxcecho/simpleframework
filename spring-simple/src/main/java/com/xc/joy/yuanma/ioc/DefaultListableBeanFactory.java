package com.xc.joy.yuanma.ioc;

import com.xc.joy.yuanma.ioc.anno.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lxcecho 909231497@qq.com
 */
public class DefaultListableBeanFactory implements BeanFactory,BeanDefinitionRegistry{

    //beanName----bean实例
    private volatile ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap();
    //beanName---beanDefintion
    private volatile ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private volatile List<String> beanNames = new ArrayList();




    @Override
    public Object getBean(String name) throws Exception {
        //从缓存中获取
        Object object = singletonObjects.get(name);
        if (object != null){

            return object;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition ==null){
            throw new Exception(" No beanName named : "+name);
        }
        //创建bean实例
        object = createBean(beanDefinition);
        if (object != null){
            // 注入依赖
            inject(object);

            //将实例化的bean放入缓存中
            singletonObjects.put(name,object);
        }

        return object;

    }

    public <T> T getBean(Class<T> requiredType) throws Exception{

        for(String beanName: beanNames){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getClassName().equals(requiredType.getName())){
                return (T) getBean(beanName);
            }
        }
        return null;
    }


    @Override
    public Object createBean(BeanDefinition beanDefinition) throws Exception {
        String className = beanDefinition.getClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new Exception(" No bean named : "+className);
        }
        return clazz.newInstance();

    }


    private void inject(Object bean) throws Exception{
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field: declaredFields){
            String fieldName = field.getName();

            if (beanNames.contains(fieldName)){
                if(field.isAnnotationPresent(Autowired.class)){
                    Object dependencyBean = getBean(fieldName);
                    //为对象中的属性赋值
                    field.setAccessible(true);
                    field.set(bean,dependencyBean);
                }
            }
        }

    }

    public void preInstantiateBeans() {
        List<String> beanNames = this.beanNames;
        for(String beanName: beanNames){
            try {
                getBean(beanName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(beanName, beanDefinition);
        beanNames.add(beanName);
    }

    public ConcurrentHashMap<String, Object> getSingletonObjects() {
        return singletonObjects;
    }

    public void setSingletonObjects(ConcurrentHashMap<String, Object> singletonObjects) {
        this.singletonObjects = singletonObjects;
    }

    public ConcurrentHashMap<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }


    public List<String> getBeanNames() {
        return beanNames;
    }

    public void setBeanNames(List<String> beanNames) {
        this.beanNames = beanNames;
    }
}
