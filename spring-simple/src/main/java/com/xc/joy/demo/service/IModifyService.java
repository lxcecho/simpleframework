package com.xc.joy.demo.service;

/**
 * @author lxcecho 909231497@qq.com
 * @since 16:44 26-06-2022
 * <p>
 * 增删改业务
 */
public interface IModifyService {

    /**
     * 增加
     */
    String add(String name, String addr);

    /**
     * 修改
     */
    String edit(Integer id, String name);

    /**
     * 删除
     */
    String remove(Integer id);

}
