package com.xc.joy.mvcdemo.service.impl;

import com.xc.joy.mvcdemo.service.DemoService;
import com.xc.joy.mvcframework.annotation.JoyService;

/**
 * @author lxcecho
 * @since 2020/12/31
 */
@JoyService
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(String name) {
        return "My Name is "+ name;
    }
}
