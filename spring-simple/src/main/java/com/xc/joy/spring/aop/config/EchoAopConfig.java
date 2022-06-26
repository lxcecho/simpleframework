package com.xc.joy.spring.aop.config;

import lombok.Data;

/**
 * Created by Tom.
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
