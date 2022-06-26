package com.xc.joy.spring.webmvc.servlet;

import com.xc.joy.spring.annotation.EchoController;
import com.xc.joy.spring.annotation.EchoRequestMapping;
import com.xc.joy.spring.context.EchoApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:49 25-06-2022
 * <p>
 * 委派模式
 * 职责：负责任务调度，请求分发
 */
public class EchoDispatcherServlet extends HttpServlet {
    private EchoApplicationContext applicationContext;

    private Map<EchoHandlerMapping, EchoHandlerAdapter> handlerAdapterMap = new HashMap<>();

    private List<EchoHandlerMapping> handlerMappings = new ArrayList<>();

    private List<EchoViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 6、委派,根据 URL 去找到一个对应的 Method 并通过 response 返回
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 完成了对 HandlerMapping 的封装，及对方法返回值的封装 ModelAndView

        // 1、通过 URL 获得一个 HandlerMapping
        EchoHandlerMapping handlerMapping = getHandlerMapping(req);

        if (handlerMapping == null) {
            processDispatcherResult(req, resp, new EchoModelAndView("404"));
            return;
        }

        // 2、根据一个 HandlerMapping 获得一个 HandlerAdapter
        EchoHandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);

        // 3、解析某一个方法的形参和返回值之后，统一封装为 ModelAndView 对象
        EchoModelAndView modelAndView = handlerAdapter.handler(req, resp, handlerMapping);

        // 就把 ModelAndView 变成一个 ViewResolver
        processDispatcherResult(req, resp, modelAndView);

    }

    private EchoHandlerAdapter getHandlerAdapter(EchoHandlerMapping handlerMapping) {
        if (this.handlerAdapterMap.isEmpty()) {
            return null;
        }
        return handlerAdapterMap.get(handlerMapping);
    }

    private void processDispatcherResult(HttpServletRequest req, HttpServletResponse resp, EchoModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }

        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (EchoViewResolver viewResolver : this.viewResolvers) {
            EchoView view = viewResolver.resolveViewName(modelAndView.getViewName());
            // 直接往浏览器输出
            view.render(modelAndView.getModel(), req, resp);
            return;
        }

    }

    private EchoHandlerMapping getHandlerMapping(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        for (EchoHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }

        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 初始化 Spring 核心 IoC 容器
        applicationContext = new EchoApplicationContext(config.getInitParameter("contextConfigLocation"));

        // 完成了 Spring IoC、DI 和 MVC 的对接

        // 初始化就打组件
        initStrategies(applicationContext);

        System.out.println("GP Spring framework is init.");
    }

    private void initStrategies(EchoApplicationContext applicationContext) {
        // 初始化 HandlerMapping
        initHandlerMappings(applicationContext);

        initHandlerAdapters(applicationContext);

        initViewResolvers(applicationContext);

    }

    private void initViewResolvers(EchoApplicationContext applicationContext) {
        String templateRoot = applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new EchoViewResolver(templateRoot));
        }

    }

    private void initHandlerAdapters(EchoApplicationContext applicationContext) {
        for (EchoHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapterMap.put(handlerMapping, new EchoHandlerAdapter());
        }
    }

    private void initHandlerMappings(EchoApplicationContext applicationContext) {
        if (this.applicationContext.getBeanDefinitionCount() == 0) {
            return;
        }

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
            Object instance = this.applicationContext.getBean(beanName);

            Class<?> clazz = instance.getClass();

            if (!clazz.isAnnotationPresent(EchoController.class)) {
                continue;
            }

            // 相当于提取 class 上配置的 url
            String baseUrl = "";
            if (clazz.isAnnotationPresent(EchoRequestMapping.class)) {
                EchoRequestMapping requestMapping = clazz.getAnnotation(EchoRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 只获取 public 的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(EchoRequestMapping.class)) {
                    continue;
                }
                //提取每个方法上面配置的url
                EchoRequestMapping requestMapping = method.getAnnotation(EchoRequestMapping.class);

                // //demo//query
                String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
//                handlerMapping.put(url, method);
                handlerMappings.add(new EchoHandlerMapping(pattern, instance, method));
                System.out.println("Mapped : " + pattern + "," + method);
            }

        }
    }

    // 自己写，自己用
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
//        if(chars[0] > )
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
