package com.xc.joy.learn.annotation.injections.qualifier;

import com.xc.joy.learn.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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

    @Bean("dao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("2");
        return dao;
    }
}
