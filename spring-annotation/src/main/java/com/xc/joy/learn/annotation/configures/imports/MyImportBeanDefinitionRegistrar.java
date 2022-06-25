package com.xc.joy.learn.annotation.configures.imports;

import com.xc.joy.learn.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata  当前类的注解信息
     * @param registry 完成BeanDefinition的注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean company = registry.containsBeanDefinition("com.gupaoedu.project.entity.Company");
        boolean member = registry.containsBeanDefinition("com.gupaoedu.project.entity.Member");

        if(company && member){
            BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
            registry.registerBeanDefinition("user",beanDefinition);
        }
    }
}
