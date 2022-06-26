package com.xc.joy.learn.service;

import com.xc.joy.learn.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:09 25-06-2022
 */
@Service
public class MyService {

//    @Qualifier("dao")
//    @Resource(name = "dao")
    @Autowired
    private MyDao myDao;

    public void print() {
        System.out.println(myDao);
    }

}
