package com.xc.joy.casedemo.dto;

import lombok.Data;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Data
public class Result<T> {

    // 请求结果状态码
    private Integer code;

    // 请求结果详情
    private String msg;

    // 请求结果返回的结果集
    private T Data;

}
