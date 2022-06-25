package com.xc.joy.learn.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:09 25-06-2022
 */
@Component
public class Train implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("火车对象销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("火车对象初始化");
    }
}
