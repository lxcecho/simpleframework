package com.xc.joy.learn.annotation.injections.value;

import com.xc.joy.learn.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
@Configuration
public class MyConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
