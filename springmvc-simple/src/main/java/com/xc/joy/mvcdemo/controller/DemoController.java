package com.xc.joy.mvcdemo.controller;

import com.xc.joy.mvcdemo.service.DemoService;
import com.xc.joy.mvcframework.annotation.JoyAutowired;
import com.xc.joy.mvcframework.annotation.JoyController;
import com.xc.joy.mvcframework.annotation.JoyRequestMapping;
import com.xc.joy.mvcframework.annotation.JoyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxcecho
 * @since 2020/12/31
 * <p>
 * 虽然用法一样，但是没有功能
 */
@JoyController
@JoyRequestMapping("/demo")
public class DemoController {

    @JoyAutowired
    private DemoService demoService;

    @JoyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp, @JoyRequestParam("name") String name) {
//		String result = demoService.get(name);
        String result = "My name is " + name;
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JoyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp, @JoyRequestParam("a") Integer a, @JoyRequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JoyRequestMapping("/sub")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @JoyRequestParam("a") Double a, @JoyRequestParam("b") Double b) {
        try {
            resp.getWriter().write(a + "-" + b + "=" + (a - b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JoyRequestMapping("/remove")
    public String remove(@JoyRequestParam("id") Integer id) {
        return "" + id;
    }

}
