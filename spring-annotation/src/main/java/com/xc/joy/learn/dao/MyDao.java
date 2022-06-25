package com.xc.joy.learn.dao;

import org.springframework.stereotype.Repository;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:09 25-06-2022
 */
@Repository
public class MyDao {

    private String flag = "1";

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "MyDao{" +
                "flag='" + flag + '\'' +
                '}';
    }
}
