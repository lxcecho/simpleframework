package com.xc.joy.spring.webmvc.servlet;

import java.util.Map;

/**
 * @author lxcecho 909231497@qq.com
 * @since 11:18 26-06-2022
 */
public class EchoModelAndView {
    private String viewName;
    private Map<String, ?> model;

    public EchoModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public EchoModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}


