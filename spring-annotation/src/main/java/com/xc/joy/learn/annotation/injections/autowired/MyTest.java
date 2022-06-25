package com.xc.joy.learn.annotation.injections.autowired;

import com.xc.joy.learn.dao.MyDao;
import com.xc.joy.learn.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:27 25-06-2022
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object controller = app.getBean("myController");
        System.out.println(controller);

        MyService service = app.getBean(MyService.class);
        service.print();

        MyDao dao = app.getBean(MyDao.class);
        System.out.println(dao);
    }
}
