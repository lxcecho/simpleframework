package com.xc.joy.annotation;

import java.io.File;

/**
 * @author lxcecho
 * @since 07.04.2021
 */
public class JoyAnnotationConfigApplicationContext {

    public void scan(String basePackage) {
        String rootPath = this.getClass().getResource("/").getPath();
        String basePackagePath = basePackage.replaceAll("\\.", "\\\\");
        File file = new File(rootPath + "//" + basePackagePath);
        String names[] = file.list();
        for (String name : names) {
            name = name.replaceAll(".class", "");
            try {

                Class clazz = Class.forName(basePackage + "." + name);
                //判斷是否是屬於@servi@xxxx
                if (clazz.isAnnotationPresent(Joy.class)) {
                    Joy joy = (Joy) clazz.getAnnotation(Joy.class);
                    System.out.println(joy.value());
                    System.out.println(clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
