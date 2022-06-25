package com.xc.joy;

import com.xc.joy.beans.Person;
import com.xc.joy.beans.PersonFactory;
import com.xc.joy.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2021/2/16
 */
public class MainApplication {
    /**
     * FactoryBean 的使用
     *
     * @param args
     */
    public static void main(String[] args) {
        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println(context.getBean("personFactory"));
        System.out.println(context.getBean("&personFactory"));*/

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PersonFactory.class);
        context.refresh();
        System.out.println(context.getBean(PersonFactory.class));
    }

}
