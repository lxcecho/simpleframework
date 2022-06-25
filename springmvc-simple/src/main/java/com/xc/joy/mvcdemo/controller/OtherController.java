package com.xc.joy.mvcdemo.controller;

import com.xc.joy.mvcdemo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxcecho
 * @since 2020/12/31
 */
public class OtherController {

    private DemoService demoService;

    public void edit(HttpServletRequest req, HttpServletResponse resp,
                     String name) {
        String result = demoService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
