package com.xc.joy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lxcecho 909231497@qq.com
 * @since 2021/2/16
 */
@MapperScan("com.xc.joy.mappers")
@SpringBootApplication
public class SpringBootSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication.class, args);
    }

}
