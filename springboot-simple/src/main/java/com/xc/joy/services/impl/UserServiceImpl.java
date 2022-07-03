package com.xc.joy.services.impl;

import com.xc.joy.entity.User;
import com.xc.joy.mappers.UserMapper;
import com.xc.joy.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:53 03-07-2022
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;  //代理对象

    @Override
    public int insert(User user) {
        //TODO 业务逻辑
        return userMapper.insert(user);
    }
}
