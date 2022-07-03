package com.xc.joy.controller;

import com.xc.joy.entity.User;
import com.xc.joy.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:53 03-07-2022
 */
@RestController
public class HelloController {

    @Autowired
    IUserService userService;

    @GetMapping("/test")
    public String test(){
        User user=new User();
        user.setName("Mic");
        userService.insert(user);
        return "Hello Spring Boot";
    }
}
