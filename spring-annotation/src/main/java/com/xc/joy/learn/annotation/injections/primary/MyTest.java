package com.xc.joy.learn.annotation.injections.primary;

import com.xc.joy.learn.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
public class MyTest {

    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);

        MyService service = app.getBean(MyService.class);
        service.print();
    }
}
