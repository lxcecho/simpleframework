package com.xc.joy.learn.annotation.configures.conditional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成");

        //假设：
        //如果操作系统是Windows，那么就将James实例化到容器中
        //如果操作系统是Linux，那么就将Tom实例化到容器中
    }

}
