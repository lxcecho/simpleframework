package com.xc.joy.learn.annotation.injections.primary;

import com.xc.joy.learn.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
@Configuration
@ComponentScan({
        "com.xc.joy.learn.controller",
        "com.xc.joy.learn.service",
        "com.xc.joy.learn.dao"
})
public class MyConfig {

    @Primary // 自动装配时当出现多个 Bean 候选者时，被注解为 @Primary 的 Bean 将作为首选，否则抛出异常
    @Bean("myDao")
    public MyDao dao() {
        MyDao dao = new MyDao();
        dao.setFlag("9");
        return dao;
    }


    @Bean("myDao")
    public MyDao myDao() {
        MyDao dao = new MyDao();
        dao.setFlag("3");
        return dao;
    }
}
