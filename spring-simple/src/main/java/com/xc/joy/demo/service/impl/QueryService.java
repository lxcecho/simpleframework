package com.xc.joy.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xc.joy.demo.service.IQueryService;
import com.xc.joy.spring.annotation.EchoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lxcecho 909231497@qq.com
 * @since 16:44 26-06-2022
 * <p>
 * 查询业务
 */
@EchoService
@Slf4j
public class QueryService implements IQueryService {

    /**
     * 查询
     */
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        log.info("这是在业务方法中打印的：" + json);
        return json;
    }

}
