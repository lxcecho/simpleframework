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
     *
     * @param name
     * @param addr
     * @return
     */
    String add(String name, String addr);

    /**
     * 修改
     *
     * @param id
     * @param name
     * @return
     */
    String edit(Integer id, String name);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    String remove(Integer id);

}
