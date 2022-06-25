package com.xc.joy.learn.annotation.configures.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //从IoC容器中拿到已经实例化的对象
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        if (osName.contains("Linux")) {
            return true;
        }
        return false;
    }
}
