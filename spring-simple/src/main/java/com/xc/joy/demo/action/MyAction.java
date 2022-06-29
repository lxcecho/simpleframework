package com.xc.joy.demo.action;

import com.xc.joy.demo.service.IModifyService;
import com.xc.joy.demo.service.IQueryService;
import com.xc.joy.spring.annotation.EchoAutowired;
import com.xc.joy.spring.annotation.EchoController;
import com.xc.joy.spring.annotation.EchoRequestMapping;
import com.xc.joy.spring.annotation.EchoRequestParam;
import com.xc.joy.spring.webmvc.servlet.EchoModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxcecho 909231497@qq.com
 * @since 16:44 26-06-2022
 * <p>
 * 公布接口 url
 */
@EchoController
@EchoRequestMapping("/web")
public class MyAction {

    @EchoAutowired
    IQueryService queryService;
    @EchoAutowired
    IModifyService modifyService;

    // localhost:8080/web/query.json/name=lxcecho
    @EchoRequestMapping("/query.json")
    public EchoModelAndView query(HttpServletRequest request, HttpServletResponse response, @EchoRequestParam("name") String name) {
        String result = queryService.query(name);
        return out(response, result);
    }

    // localhost:8080/web/addEcho.json/name=lxcecho&&addr=GuangZhou
    @EchoRequestMapping("/add*.json")
    public EchoModelAndView add(HttpServletRequest request, HttpServletResponse response, @EchoRequestParam("name") String name, @EchoRequestParam("addr") String addr) {
        String result = modifyService.add(name, addr);
        return out(response, result);
    }

    // localhost:8080/web/remove.json/id=123
    @EchoRequestMapping("/remove.json")
    public EchoModelAndView remove(HttpServletRequest request, HttpServletResponse response, @EchoRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    // localhost:8080/web/edit.json/id=123&&name=Jerry
    @EchoRequestMapping("/edit.json")
    public EchoModelAndView edit(HttpServletRequest request, HttpServletResponse response, @EchoRequestParam("id") Integer id, @EchoRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }

    private EchoModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
