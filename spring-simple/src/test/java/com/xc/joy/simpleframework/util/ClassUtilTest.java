package com.xc.joy.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author lxcecho
 * @since 2021/1/5
 */
public class ClassUtilTest {
    @DisplayName("提取目标类方法：extractPackageClassTest")
    @Test
    public void extractPackageClassTest(){
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.xc.justforjoy.casedemo.entity");
        System.out.println(classSet);
        Assertions.assertEquals(2,classSet.size());
    }
}
