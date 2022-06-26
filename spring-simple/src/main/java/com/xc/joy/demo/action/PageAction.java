package com.xc.joy.demo.action;

import com.xc.joy.demo.service.IQueryService;
import com.xc.joy.spring.annotation.EchoAutowired;
import com.xc.joy.spring.annotation.EchoController;
import com.xc.joy.spring.annotation.EchoRequestMapping;
import com.xc.joy.spring.annotation.EchoRequestParam;
import com.xc.joy.spring.webmvc.servlet.EchoModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxcecho 909231497@qq.com
 * @since 16:44 26-06-2022
 * <p>
 * 公布接口url
 */
@EchoController
@EchoRequestMapping("/")
public class PageAction {

    @EchoAutowired
    IQueryService queryService;

    @EchoRequestMapping("/first.html")
    public EchoModelAndView query(@EchoRequestParam("teacher") String teacher) {
        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new EchoModelAndView("first.html", model);
    }

}
