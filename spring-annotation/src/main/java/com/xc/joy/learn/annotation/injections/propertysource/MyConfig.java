package com.xc.joy.learn.annotation.injections.propertysource;

import com.xc.joy.learn.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
@Configuration
@PropertySource("classpath:values.properties")
public class MyConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
