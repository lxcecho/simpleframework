package com.xc.joy.learn.entity;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:09 25-06-2022
 */
public class Car {
    public Car() {
        System.out.println("调用Car的构造方法");
    }

    public void addOil() {
        System.out.println("行驶前加油");
    }

    public void run() {
        System.out.println("正在开车");
    }

    public void close() {
        System.out.println("停车熄火");
    }
}
