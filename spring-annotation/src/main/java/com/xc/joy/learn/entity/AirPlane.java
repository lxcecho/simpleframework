package com.xc.joy.learn.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:09 25-06-2022
 */
@Component
public class AirPlane {

    public AirPlane() {
        System.out.println("调用AirPlane的构造方法");
    }

    @PostConstruct
    public void addOil() {
        System.out.println("飞机飞行前加油");
    }

    public void run() {
        System.out.println("正在空中巡航");
    }

    @PreDestroy
    public void close() {
        System.out.println("飞机落地熄火");
    }

}
