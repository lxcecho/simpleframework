package com.xc.joy.learn.controller;

import com.xc.joy.learn.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:08 25-06-2022
 */
@Controller
public class MyController {

    @Autowired
    private MyService myService;

}
