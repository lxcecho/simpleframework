package com.xc.joy.learn.annotation.configures.lifecycle;

import com.xc.joy.learn.entity.Car;
import org.springframework.context.annotation.*;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
@ComponentScans({
        @ComponentScan("com.xc.joy.learn.entity"),
        @ComponentScan("com.xc.joy.learn.annotation.configures.lifecycle")
})
@Configuration
public class MyConfig {

    @Lazy
    @Bean(initMethod = "addOil",destroyMethod = "close")
    public Car car(){
        return new Car();
    }

    // 3种方式
    // 1.添加 initMethod 和 destroyMethod
    // 2.实现 InitializingBean 和 DisposableBean 接口
    // 3.使用 @PostConstruct 和 @PreDestroy 注解
}
