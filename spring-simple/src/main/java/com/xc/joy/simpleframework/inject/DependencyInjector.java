package com.xc.joy.simpleframework.inject;

import com.xc.joy.simpleframework.core.BeanContainer;
import com.xc.joy.simpleframework.inject.annotation.Autowired;
import com.xc.joy.simpleframework.util.ClassUtil;
import com.xc.joy.simpleframework.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author lxcecho
 * @since 2021/1/5
 */
@Slf4j
public class DependencyInjector {

    // Bean容器
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    // 执行IOC
    public void doIoc() {
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("empty classSet in BeanContainer ");
            return;
        }
        // 1 遍历Bean容器中所有的Class对象
        for (Class<?> clazz : classSet) {
            // 2 遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                // 3 超出被 Autowired 标记的成员变量
                Autowired autowired = field.getAnnotation(Autowired.class);
                String value = autowired.value();
                // 4 获取这些成员变量的类型
                Class<?> fieldTypeClass = field.getType();
                // 5 获取这些成员变量的类型在容器中对应的实例
                Object fieldValue = getFieldInstance(fieldTypeClass, value);
                if (fieldValue == null) {
                    throw new RuntimeException("unable to inject relevant type，target fieldClass is:" + fieldTypeClass.getName() + " autowiredValue is : " + value);
                } else {
                    // 6 通过反射将对应的 成员变量实例 注入到 成员变量所在的类的实例里
                    Object targetBean = beanContainer.getBean(clazz);
                    ClassUtil.setField(field, targetBean, fieldValue, true);
                }
            }
        }
    }

    /**
     * 根据Class在beanContainer里获取其实例或者实现类
     *
     * @param fieldTypeClass
     * @param value
     * @return
     */
    private Object getFieldInstance(Class<?> fieldTypeClass, String value) {
        Object fieldValue = beanContainer.getBean(fieldTypeClass);
        if (fieldValue != null) {
            return fieldValue;
        } else {
            Class<?> implementedClass = getImplementedClass(fieldTypeClass, value);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            } else {
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     *
     * @param fieldTypeClass
     * @param value
     * @return
     */
    private Class<?> getImplementedClass(Class<?> fieldTypeClass, String value) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldTypeClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(value)) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    // 入股多余两个实现类且用户未指定其中一个实例类，则抛出异常
                    throw new RuntimeException("multiple implemented classes for " + fieldTypeClass.getName() + " please set @Autowired's value to pick one");
                }
            } else {
                for (Class<?> clazz : classSet) {
                    if (value.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }


}
