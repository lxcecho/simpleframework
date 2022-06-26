package com.xc.joy.spring.webmvc.servlet;

import java.util.regex.Pattern;

import java.lang.reflect.Method;

/**
 * @author lxcecho 909231497@qq.com
 * @since 11:18 26-06-2022
 */
public class EchoHandlerMapping {

    /**
     * URL
     */
    private Pattern pattern;

    /**
     * 对应的 Method
     */
    private Method method;

    /**
     * Method 对应的实例对象
     */
    private Object controller;

    public EchoHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

}
