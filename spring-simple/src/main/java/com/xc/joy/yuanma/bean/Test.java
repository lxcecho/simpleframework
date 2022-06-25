package com.xc.joy.yuanma.bean;

import com.xc.joy.yuanma.ioc.ApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context =  new ApplicationContext(AppConfig.class);
        System.out.println(context.getBean(Fox.class));
    }


}
