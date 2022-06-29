package com.xc.joy.demo.service.impl;

import com.xc.joy.demo.service.IModifyService;
import com.xc.joy.spring.annotation.EchoService;

/**
 * @author lxcecho 909231497@qq.com
 * @since 16:44 26-06-2022
 * <p>
 * 增删改业务
 */
@EchoService
public class ModifyService implements IModifyService {

    /**
     * 增加
     */
    public String add(String name, String addr) {
//        return "modifyService add,name=" + name + ",addr=" + addr;
        // 测试 aop ，抛出异常
        throw new RuntimeException("This is my custom exception.");
    }

    /**
     * 修改
     */
    public String edit(Integer id, String name) {
        return "modifyService edit,id=" + id + ",name=" + name;
    }

    /**
     * 删除
     */
    public String remove(Integer id) {
        return "modifyService id=" + id;
    }

}
