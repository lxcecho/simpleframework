package com.xc.joy.spring.aop.config;

import lombok.Data;

/**
 * @author lxcecho 909231497@qq.com
 * @since 21:42 26-06-2022
 */
@Data
public class EchoAopConfig {

    private String pointCut;

    private String aspectClass;

    private String aspectBefore;

    private String aspectAfter;

    private String aspectAfterThrow;

    private String aspectAfterThrowingName;

}
