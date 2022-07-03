package com.xc.joy.mappers;

import com.xc.joy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:53 03-07-2022
 */
@Mapper
public interface UserMapper {

    int insert(User user);
}
